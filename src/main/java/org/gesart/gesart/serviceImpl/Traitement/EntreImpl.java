package org.gesart.gesart.serviceImpl.Traitement;

import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.Exception.CommandeFournisseurException;
import org.gesart.gesart.Exception.MagasinNotFoundException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.traitement.BonDeCmdeFourDto;
import org.gesart.gesart.dto.traitement.EntreDto;
import org.gesart.gesart.repository.parametrage.FournisseurRepository;
import org.gesart.gesart.repository.parametrage.MagasinRepository;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.traitement.EntreRepository;
import org.gesart.gesart.service.EntreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public EntreDto createAndUpdateEntreMag(final EntreDto dto) {
		try {
			// Validation des entrées
			if (dto.getFournisseurId() == null) {
				throw new IllegalArgumentException("L'ID du fournisseur est obligatoire");
			}
			// Mapping DTO -> Entité
			Entre entre = mapper.map(dto, Entre.class);

			// Association du magasin
			Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
					.orElseThrow(() -> new MagasinNotFoundException("fournisseur introuvable"));
			entre.setFournisseur(fournisseur);

			// Sauvegarde de l'entrée en magasin
			entre = entreRepository.save(entre);

			// Gestion des produits
			if (dto.getProduitDtos() != null && !dto.getProduitDtos().isEmpty()) {
				Entre finalEntre = entre;
				dto.getProduitDtos().forEach(produitDto -> {
					Produit produit = mapper.map(produitDto, Produit.class);
					// Vérifier si le produit existe déjà
					Produit existingProduit = produitRepository.findById(produitDto.getId()).orElse(null);
					if (existingProduit != null) {
						// Mettre à jour le stock et le prix
						existingProduit.setStockProduit(existingProduit.getStockProduit().add(finalEntre.getQteEntre()));
						existingProduit.setPrixActuel(finalEntre.getPrixEntre());
						produitRepository.save(existingProduit);
					} else {
						// Créer un nouveau produit
						produit.setStockProduit(finalEntre.getQteEntre());
						produit.setPrixActuel(finalEntre.getPrixEntre());
						produit.setEntre(finalEntre); // Associer le produit à l'entrée en magasin
						produitRepository.save(produit);
					}
				});
			}

			// Retour du DTO mis à jour
			return mapper.map(entre, EntreDto.class);
		} catch (Exception e) {
			log.error("Erreur lors de la création/mise à jour d'une entrée en magasin : {}", e.getMessage());
			throw new CommandeFournisseurException("Erreur lors de la création/mise à jour d'une entrée en magasin", e);
		}
	}


	@Override
	public List<EntreDto> fetchEntre() {
		return null;
	}
}
