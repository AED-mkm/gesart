package org.gesart.gesart.serviceImpl.Traitement;

import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.domain.enums.TypeVente;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.domain.traitement.Facture;
import org.gesart.gesart.domain.traitement.LigneDeVente;
import org.gesart.gesart.domain.traitement.Vente;
import org.gesart.gesart.dto.traitement.LigneVenteDto;
import org.gesart.gesart.dto.traitement.VenteDto;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.parametrage.TaxeRepository;
import org.gesart.gesart.repository.traitement.FactureRepository;
import org.gesart.gesart.repository.traitement.LigneVteRepository;
import org.gesart.gesart.repository.traitement.VenteRepository;
import org.gesart.gesart.service.VenteService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class VenteImpl implements VenteService {

	private final VenteRepository venteRepository;
	private final ProduitRepository produitRepository;
	private final FactureRepository factureRepository;
	private final LigneVteRepository ligneVteRepository;
	private final TaxeRepository taxeRepository;
	private final Mapper mapper;


	@Autowired
	private FactureUtils factureUtils;


	@Override
	@Transactional
	public VenteDto createAndUpdateVenteMag(VenteDto dto) {
		if (dto.getLigneVenteDtos() == null || dto.getLigneVenteDtos().isEmpty()) {
			throw new ValidationException("La liste des lignes de vente ne peut pas être vide.");
		}
		if (dto.getClientId() == null) {
			throw new ValidationException("Le champ client est obligatoire.");
		}
		Vente vente = mapper.map(dto, Vente.class);
		vente.setDateVente(LocalDate.now());
		vente.setTypeVente(TypeVente.F);
		venteRepository.save(vente);
		BigDecimal prixTotalVente = BigDecimal.ZERO;

		try {
			for (LigneVenteDto ligneVenteDto : dto.getLigneVenteDtos()) {
				Produit produit = produitRepository.findById(ligneVenteDto.getProduitId())
						.orElseThrow(() -> new ProduitNotFoundException("Produit introuvable"));

				if (ligneVenteDto.getQteVente() == null || ligneVenteDto.getQteVente().compareTo(BigDecimal.ZERO) <= 0) {
					throw new ValidationException("La quantité du produit doit être positive.");
				}

				if (ligneVenteDto.getQteVente().compareTo(produit.getStockProduit()) > 0) {
					throw new ValidationException("La quantité saisie est supérieure à la quantité en stock!");
				}

				ligneVenteDto.setPrixUnitaire(produit.getPrixActuel());
				BigDecimal prixTotal = ligneVenteDto.getPrixUnitaire().multiply(ligneVenteDto.getQteVente());
				BigDecimal resProduit = produit.getStockProduit().subtract(ligneVenteDto.getQteVente());
				produit.setStockProduit(resProduit);
				produitRepository.save(produit);
				LigneDeVente ligneDeVente1 = mapper.map(ligneVenteDto, LigneDeVente.class); // Mapper LigneVenteDto
				ligneDeVente1.setPrixTotal(prixTotal); // Utiliser le prix total de la ligne de vente
				ligneDeVente1.setVente(vente);
				prixTotalVente = prixTotalVente.add(prixTotal);
				ligneVteRepository.save(ligneDeVente1);
			}
			// Calcul des montants HT, TVA et BIC
			BigDecimal montantTva = BigDecimal.ZERO;
			BigDecimal montantBic = BigDecimal.ZERO;
			final BigDecimal cent = BigDecimal.valueOf(100);

			if (dto.getTaxesCochees() != null && !dto.getTaxesCochees().isEmpty()) {
				List<Taxe> taxes = taxeRepository.findAll().stream()
						.filter(taxe -> dto.getTaxesCochees().contains(taxe.getCode()))
						.collect(Collectors.toUnmodifiableList());

				for (Taxe taxe : taxes) {
					BigDecimal taux = taxe.getTaxe().divide(cent, 2, BigDecimal.ROUND_HALF_UP);
					if (taxe.getCode().equalsIgnoreCase("01")) {
						montantTva = prixTotalVente.multiply(taux);
						log.info("TVA:"+montantTva);
						log.info("TAUX TVA:"+taux);
					} else if (taxe.getCode().equalsIgnoreCase("02")) {
						montantBic = prixTotalVente.multiply(taux);
						log.info("BIC:"+montantBic);
						log.info("TAUX BIC:"+taux);
					}
				}
				BigDecimal montantTtc = prixTotalVente.add(montantTva).add(montantBic);
				vente.setMontantHt(prixTotalVente);
				vente.setMontantTva(montantTva);
				vente.setMontantBic(montantBic);
				vente.setMontantTTC(montantTtc);
			}


			// Création de la facture
			Facture facture = new Facture();
			facture.setClient(vente.getClient());
			facture.setDateFacture(LocalDate.now());
			FactureUtils factureUtils = new FactureUtils(); // Création de FactureUtils avec magasinId
			facture.setNumFacture(factureUtils.genererNumeroFacture());
			vente.setObjet("FAC"+ " " + factureUtils.genererNumeroFacture());
			factureRepository.save(facture);
			vente.setFactureId(facture.getId());
			return mapper.map(vente, VenteDto.class);
		} catch (ProduitNotFoundException | ValidationException e) {
			log.warn("Validation échouée : {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Erreur lors de la création/mise à jour de la vente : {}", e.getMessage(), e);
			throw new ServiceException("Erreur lors de la création/mise à jour de la vente.", e);
		}
	}

	/**
	 * @return
	 */
	@Override
	public List<VenteDto> fetchVente() {
		return null;
	}


}
