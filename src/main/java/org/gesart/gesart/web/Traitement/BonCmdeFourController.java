package org.gesart.gesart.web.Traitement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gesart.gesart.dto.traitement.BonDeCmdeFourDto;
import org.gesart.gesart.dto.traitement.ProdBonCmdeFourDto;
import org.gesart.gesart.service.BonDeCmdeFourService;
import org.gesart.gesart.Exception.BonDeCmdeFourNotFoundException;

import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BonCmdeFourController  {
	private final BonDeCmdeFourService bonDeCmdeFourService;
	@PostMapping(path = "/bon-de-commande")
	public ResponseEntity<BonDeCmdeFourDto> createAndUpdateBonCmdeFour(
			@Valid @RequestBody final BonDeCmdeFourDto dto) {
		return new ResponseEntity<>(bonDeCmdeFourService.createAndUpdateBonDeCmdeFour(dto), HttpStatus.CREATED);
	}

	/**
	 * Ajouter un produit à la liste de produits ProdBonCmdeFourDto
	 * @param id
	 * @param prodDto
	 * @return prodDto
	 */
	@PostMapping("/bon-de-commande/{id}/produits")
	public ResponseEntity<Void> ajouterProduit(@PathVariable Long id, @RequestBody ProdBonCmdeFourDto prodDto)  {
		try {
			bonDeCmdeFourService.ajouterProduit(id, prodDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Ajouter un produit à la liste de produits ProdBonCmdeFourDto
	 * @param id
	 * @param prodId
	 * @return prodDto
	 */

	@DeleteMapping("/bon-de-commande/{id}/produits/{prodId}")
	public ResponseEntity<Void> supprimerProduit( @PathVariable Long id, @PathVariable Long prodId) {
		try {
			bonDeCmdeFourService.supprimerProduit(id, prodId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@PutMapping(path = "/bon-de-commande")
	public ResponseEntity<BonDeCmdeFourDto> updateCmdeFour(
			@Valid @RequestBody final BonDeCmdeFourDto dto) {
		return new ResponseEntity<>(bonDeCmdeFourService.createAndUpdateBonDeCmdeFour(dto), HttpStatus.CREATED);
	}
	@GetMapping("/bon-de-commande/all")
	public ResponseEntity<List<BonDeCmdeFourDto>> listeCmdeFours() {
		return new ResponseEntity<>(bonDeCmdeFourService.fetchBonCmdeFour(), HttpStatus.OK);
	}

	/**
	 * Liste des produits d'une commande
	 * @param bonCmdeId
	 * @return List<ProdBonCmdeFourDto>
	 */
	@GetMapping("/produits/{bonCmdeId}")
	public ResponseEntity<List<ProdBonCmdeFourDto>> getProduitsByCommandeId(@PathVariable Long bonCmdeId) {
		List<ProdBonCmdeFourDto> produits = bonDeCmdeFourService.getProduitsByCommandeId(bonCmdeId);
		return ResponseEntity.ok(produits);
	}

}
