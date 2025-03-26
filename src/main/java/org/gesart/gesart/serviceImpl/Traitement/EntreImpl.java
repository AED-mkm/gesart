package org.gesart.gesart.serviceImpl.Traitement;

import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.Exception.FournisseurNotFoundException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.domain.historique.HistoriquePrix;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.traitement.EntreProduit;

import org.gesart.gesart.dto.traitement.EntreDto;
import org.gesart.gesart.dto.traitement.EntreProduitDto;
import org.gesart.gesart.repository.historique.HistoriquePrixRepository;
import org.gesart.gesart.repository.parametrage.FournisseurRepository;
import org.gesart.gesart.repository.parametrage.MagasinRepository;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.traitement.EntreProduitRepository;
import org.gesart.gesart.repository.traitement.EntreRepository;
import org.gesart.gesart.service.EntreService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class EntreImpl implements EntreService {
	private final Mapper mapper;
	private final EntreRepository entreRepository;
	private final MagasinRepository magasinRepository;
	private final ProduitRepository produitRepository;
	private final FournisseurRepository fournisseurRepository;
	private final HistoriquePrixRepository historiquePrixRepository;
	private final EntreProduitRepository entreProduitRepository;

	@Transactional
	public EntreDto createAndUpdateEntreMag(final EntreDto dto) {
		if (dto.getFournisseurId() == null) {
			throw new ValidationException("L'ID du fournisseur ne peut pas être nul.");
		}
		if (dto.getEntreProduitsDto() == null || dto.getEntreProduitsDto().isEmpty()) {
			throw new ValidationException("La liste des produits ne peut pas être vide.");
		}

		try {
			Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
					.orElseThrow(() -> new FournisseurNotFoundException("Fournisseur introuvable."));

			Entre entree = mapper.map(dto, Entre.class);
			entree.setFournisseur(fournisseur);
			entree.setDateEnt(LocalDate.now());

			// Sauvegarde initiale
			entree = entreRepository.save(entree);
			List<EntreProduit> entreProduits = new ArrayList<>();
			for (EntreProduitDto produitDto : dto.getEntreProduitsDto()) {
				Produit produit = produitRepository.findById(produitDto.getProduitId())
						.orElseThrow(() -> new ProduitNotFoundException("Produit introuvable"));

				if (produitDto.getQuantite() == null || produitDto.getQuantite().compareTo(BigDecimal.ZERO) <= 0) {
					throw new ValidationException("La quantité du produit doit être positive.");
				}
				if (produitDto.getPrixEntre() == null || produitDto.getPrixEntre().compareTo(BigDecimal.ZERO) <= 0) {
					throw new ValidationException("Le prix du produit doit être positif.");
				}

				BigDecimal ancienPrix = produit.getPrixActuel();
				BigDecimal nouveauPrix = produit.getPrixActuel();
				produit.setAncienPrix(ancienPrix);



				// Mise à jour du stock global du produit
				produit.setStockProduit(produit.getStockProduit().add(produitDto.getQuantite()));
				produit.setPrixActuel(produitDto.getPrixEntre());
				produitRepository.save(produit);

				// Ajout de l'historique des prix
				HistoriquePrix historiquePrix = new HistoriquePrix();
				historiquePrix.setProduit(produit);
				historiquePrix.setNouveauPrix(produitDto.getPrixEntre());
				historiquePrix.setAncienPrix(ancienPrix);
				historiquePrixRepository.save(historiquePrix);

				EntreProduit entreProduit = new EntreProduit();
				entreProduit.setEntre(entree);
				entreProduit.setProduit(produit);
				entreProduit.setQuantite(produitDto.getQuantite());
				entreProduit.setPrixEntre(produitDto.getPrixEntre());

				entreProduits.add(entreProduit);
			}
			entreProduitRepository.saveAll(entreProduits);
			// Associer les produits et sauvegarder l'entrée finale
			entree.setEntreProduits(entreProduits);
			return mapper.map(entree, EntreDto.class);

		} catch (FournisseurNotFoundException | ProduitNotFoundException | ValidationException e) {
			log.warn("Validation échouée : {}", e.getMessage());
			throw e; // Ne pas encapsuler des exceptions métier
		} catch (Exception e) {
			log.error("Erreur lors de la création/mise à jour d'une entrée en magasin", e);
			throw new ServiceException("Une erreur est survenue lors du traitement de votre demande.", e);
		}
	}

	@Override
	public List<EntreDto> fetchEntre() {
		return null;
	}
}
