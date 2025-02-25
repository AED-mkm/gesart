package org.gesart.gesart.serviceImpl.Traitement;

import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.traitement.BonDeCmdeFour;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.dto.traitement.BonDeCmdeFourDto;
import org.gesart.gesart.dto.traitement.ProdBonCmdeFourDto;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.traitement.BonDeCmdeFourRepository;
import org.gesart.gesart.repository.traitement.ProdBonCmdeFourRepository;
import org.gesart.gesart.Exception.BonDeCmdeFourNotFoundException;
import org.gesart.gesart.Exception.CommandeFournisseurException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.service.BonDeCmdeFourService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class BonDeCmdeFourImpl implements BonDeCmdeFourService {

	private final Mapper mapper;
	private final BonDeCmdeFourRepository bonDeCmdeFourRepository;
	private final ProdBonCmdeFourRepository prodBonCmdeFourRepository;
	private final ProduitRepository produitRepository;


	private BigDecimal calculerTotalCommande(List<ProdBonCmdeFour> produits) {
		return produits.stream()
				.map(ProdBonCmdeFour::getMontantProdCmde)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	/**
	 * @param dto
	 *
	 * @return BonDeCmdeFourDto
	 */
	@Override
	@Transactional
	public BonDeCmdeFourDto createAndUpdateBonDeCmdeFour(final BonDeCmdeFourDto dto) {
		try {
			// Validation des entrées
			if (dto.getFournisseurId() == null) {
				throw new IllegalArgumentException("L'ID du fournisseur est obligatoire");
			}
			if (dto.getMagasinId() == null) {
				throw new IllegalArgumentException("L'ID du magasin est obligatoire");
			}
			if (dto.getProdBonCmdeFourDto() == null || dto.getProdBonCmdeFourDto().isEmpty()) {
				throw new IllegalArgumentException("La liste des produits ne peut pas être vide");
			}
			// Mapping DTO -> Entité
			BonDeCmdeFour bonDeCmdeFour = mapper.map(dto, BonDeCmdeFour.class);
			// Sauvegarde du bon de commande
			bonDeCmdeFour = bonDeCmdeFourRepository.save(bonDeCmdeFour);
			if (dto.getProdBonCmdeFourDto() != null && !dto.getProdBonCmdeFourDto().isEmpty()) {
				BonDeCmdeFour finalBonDeCmdeFour = bonDeCmdeFour;
				BonDeCmdeFour finalBonDeCmdeFour1 = bonDeCmdeFour;
				// Gestion des produits
				List<ProdBonCmdeFour> prodBonCmdeFours = dto.getProdBonCmdeFourDto().stream()
						.map(prodDto -> {
							ProdBonCmdeFour prod = mapper.map(prodDto, ProdBonCmdeFour.class);
							prod.setBonDeCmdeFour(finalBonDeCmdeFour);
							prod.setMagasin(finalBonDeCmdeFour1.getMagasin());
							Produit produit = produitRepository.findById(prodDto.getProduitId()).orElseThrow(() ->
									new ProduitNotFoundException("Produit introuvable"));
							prod.setProduit(produit);
							prod.setMontantProdCmde(prod.getPrixProdCmde().multiply(prod.getQteProdCmde()));
							return prod;
						})
						.collect(Collectors.toList());
				// Sauvegarde des produits
				prodBonCmdeFourRepository.saveAll(prodBonCmdeFours);
				// Calcul du total de la commande en utilisant la méthode encapsulée
				BigDecimal totalCmdeF = calculerTotalCommande(prodBonCmdeFours);
				bonDeCmdeFour.setTotalCmdeFour(totalCmdeF);
			}
			// Retour du DTO mis à jour
			return mapper.map(bonDeCmdeFour, BonDeCmdeFourDto.class);
		} catch (Exception e) {
			log.error("Erreur lors de la création/mise à jour de la commande fournisseur : {}", e.getMessage());
			throw new CommandeFournisseurException("Erreur lors de la création/mise à jour de la commande fournisseur", e);
		}
	}

	@Transactional
	public void ajouterProduit(Long bonDeCmdeFourId, ProdBonCmdeFourDto prodDto) {
		// 1. Récupérer le bon de commande
		BonDeCmdeFour bonDeCmdeFour = bonDeCmdeFourRepository.findById(bonDeCmdeFourId)
				.orElseThrow(() -> new BonDeCmdeFourNotFoundException("Bon de commande introuvable"));

		// 2. Convertir le DTO en entité
		ProdBonCmdeFour prod = mapper.map(prodDto, ProdBonCmdeFour.class);

		// 3. Associer le produit au bon de commande et au magasin
		prod.setBonDeCmdeFour(bonDeCmdeFour);
		prod.setMagasin(bonDeCmdeFour.getMagasin());

		// 4. Récupérer le produit associé
		prod.setProduit(produitRepository.findById(prodDto.getProduitId())
				.orElseThrow(() -> new ProduitNotFoundException("Produit introuvable")));

		// 5. Calculer le montant du produit
		BigDecimal montant = prod.getPrixProdCmde().multiply(prod.getQteProdCmde());
		prod.setMontantProdCmde(montant);

		// 6. Ajouter le produit à la liste
		bonDeCmdeFour.getProdBonCmdeFours().add(prod);

		// 7. Recalculer le total de la commande
		BigDecimal totalCommande = calculerTotalCommande(bonDeCmdeFour.getProdBonCmdeFours());
		bonDeCmdeFour.setTotalCmdeFour(totalCommande);

		// 8. Sauvegarder les modifications
		bonDeCmdeFourRepository.save(bonDeCmdeFour);
	}

	@Transactional
	public void supprimerProduit(Long bonDeCmdeFourId, Long prodId) {
		// 1. Récupérer le bon de commande
		BonDeCmdeFour bonDeCmdeFour = bonDeCmdeFourRepository.findById(bonDeCmdeFourId)
				.orElseThrow(() -> new BonDeCmdeFourNotFoundException("Bon de commande introuvable"));

		// 2. Trouver le produit à supprimer
		ProdBonCmdeFour prod = bonDeCmdeFour.getProdBonCmdeFours().stream()
				.filter(p -> p.getId().equals(prodId))
				.findFirst()
				.orElseThrow(() -> new ProduitNotFoundException("Produit introuvable dans la commande"));

		// 3. Supprimer le produit de la liste
		bonDeCmdeFour.getProdBonCmdeFours().remove(prod);

		// 4. Recalculer le total de la commande
		BigDecimal totalCommande = calculerTotalCommande(bonDeCmdeFour.getProdBonCmdeFours());
		bonDeCmdeFour.setTotalCmdeFour(totalCommande);

		// 5. Sauvegarder les modifications
		bonDeCmdeFourRepository.save(bonDeCmdeFour);

		// 6. Supprimer le produit de la base de données
		prodBonCmdeFourRepository.delete(prod);
	}

	/**
	 *
	 * @param commandeId
	 * @return
	 */

	public List<ProdBonCmdeFourDto> getProduitsByCommandeId(Long commandeId) {
		BonDeCmdeFour commande = bonDeCmdeFourRepository.findById(commandeId)
				.orElseThrow(()
						-> new CommandeFournisseurException("Commande fournisseur introuvable avec l'ID : " + commandeId));
		return commande.getProdBonCmdeFours().stream()
				.map(prod -> mapper.map(prod, ProdBonCmdeFourDto.class))
				.collect(Collectors.toList());
	}

	public List<BonDeCmdeFourDto> fetchBonCmdeFour() {
		return produitRepository.findAll().stream().map(BonDeCmde ->
				mapper.map(BonDeCmde, BonDeCmdeFourDto.class)).collect(Collectors.toList());
	}


}

