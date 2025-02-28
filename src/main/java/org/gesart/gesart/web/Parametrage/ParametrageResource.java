package org.gesart.gesart.web.Parametrage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gesart.gesart.Exception.BonDeCmdeFourNotFoundException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.domain.parametrage.TypeReglement;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.TaxeDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.dto.parametrage.TypeReglDto;
import org.gesart.gesart.security.AuthoritiesConstants;
import org.gesart.gesart.serviceImpl.Parametrage.ParametreService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * The type parametrage resource.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParametrageResource {
	private final ParametreService parametreService;

	/**
	 * .
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PostMapping(path = "/magasins")
	public ResponseEntity<MagasinDto> createMag(
			@Valid @RequestBody final MagasinDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateMg(dto), HttpStatus.CREATED);
	}


	/**
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/magasins")
	public ResponseEntity<MagasinDto> updateMag(
			@Valid @RequestBody final MagasinDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateMg(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste magasins response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/magasins")
	public ResponseEntity<List<MagasinDto>> listeMagasins() {
		return new ResponseEntity<>(parametreService.fetchMagasins(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return page
	 */
	@GetMapping("magasins/page")
	public ResponseEntity<Page<Magasin>> allpage() {
		return new ResponseEntity<>(parametreService
				.findPageMagasin(0, 5, "createdDate"), HttpStatus.OK);
	}


	@DeleteMapping("/magasins/{id}")
	public ResponseEntity<Void> supprimerMagasin( @PathVariable Long id) {
		try {
			parametreService.deleteMagasin(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/magasins/{idMagasin}")
	public Optional<Magasin> geMagasinbyId(@PathVariable Long idMagasin) {
		return parametreService.findMagasinById(idMagasin);
	}

	/**
	 * retourne le magasin de l'utilisateur connecté
	 * @param id
	 * @return magasin
	 */


	@GetMapping("/{id}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or (\"" + AuthoritiesConstants.PROPRIETAIRE + "\")" +
			"and #id == @parametreService.getMagasinId(principal.id))")
		public ResponseEntity<Magasin> getMagasin(@PathVariable Long id) {
			Optional<Magasin> magasin = parametreService.findById(id);
			if (magasin.isPresent()) {
				return ResponseEntity.ok(magasin.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		}

	/**
	 * .
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PostMapping(path = "/banques")
	public ResponseEntity<BanqueDto> createBanque(
			@Valid @RequestBody final BanqueDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateBanque(dto), HttpStatus.CREATED);
	}
	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/banques")
	public ResponseEntity<BanqueDto> updateMag(
			@Valid @RequestBody final BanqueDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateBanque(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste magasins response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/banques")
	public ResponseEntity<List<BanqueDto>> listeBanques() {
		return new ResponseEntity<>(parametreService.fetchBanques(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("banques/page")
	public ResponseEntity<Page<Banque>> allpageBanque() {
		return new ResponseEntity<>(parametreService
				.findPageBanque(0, 5, "createdDate"), HttpStatus.OK);
	}
	@DeleteMapping("/banques/{id}")
	public ResponseEntity<Void> supprimerBanque( @PathVariable Long id) {
		try {
			parametreService.deleteBanque(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@GetMapping("/banques/{idBanaue}")
	public Optional<Banque> geBanquebyId(@PathVariable Long idBanaue) {
		return parametreService.findBanqueById(idBanaue);
	}



	/**
	 * .
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PostMapping(path = "/produits")
	public ResponseEntity<ProduitDto> createprod(
			@Valid @RequestBody final ProduitDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateProd(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/produits")
	public ResponseEntity<ProduitDto> updateMag(
			@Valid @RequestBody final ProduitDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateProd(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste magasins response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/produits")
	public ResponseEntity<List<ProduitDto>> listeProduits() {
		return new ResponseEntity<>(parametreService.fetchProduits(), HttpStatus.OK);
	}
	/**.
	 * page
	 * @return page
	 */

	@GetMapping("produits/page")
	public ResponseEntity<Page<Produit>> allpageProduit() {
		return new ResponseEntity<>(parametreService
				.findPageProduit(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/produits/{id}")
	public ResponseEntity<Void> supprimerproduit( @PathVariable Long id) {
		try {
			parametreService.deleteProduit(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



	@GetMapping("/produits/{idProd}")
	public Optional<Produit> getProduitById(@PathVariable Long idProd) {
		return parametreService.findProduitById(idProd);
	}

	/**
	 * .
	 * creation des clients
	 *
	 * @param dto the dto
	 *
	 * @return ClientDto response entity
	 */
	@PostMapping(path = "/clients")
	public ResponseEntity<ClientDto> createClt(
			@Valid @RequestBody final ClientDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateClient(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/clients")
	public ResponseEntity<ClientDto> updateClt(
			@Valid @RequestBody final ClientDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateClient(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste clients response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/clients")
	public ResponseEntity<List<ClientDto>> listeClients() {
		return new ResponseEntity<>(parametreService.fetchClients(), HttpStatus.OK);
	}

	/**
	 * retourne un client par idclient
	 *
	 * @param idClient
	 *
	 * @return client
	 */
	@GetMapping("/clients/{idClient}")
	public Client getClientById(@PathVariable Long idClient) {
		 return parametreService.findClientById(idClient);
	}

	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Void> supprimerClient( @PathVariable Long id) {
		try {
			parametreService.deleteClient(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	/**
	 * .
	 * creation de type client
	 *
	 * @param dto the dto
	 *
	 * @return TypeClientDto response entity
	 */
	@PostMapping(path = "/type_clients")
	public ResponseEntity<TypeClientDto> createTypeClt(
			@Valid @RequestBody final TypeClientDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeClt(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/type_clients")
	public ResponseEntity<TypeClientDto> updateTypeClt(
			@Valid @RequestBody final TypeClientDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeClt(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste typeclients response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/type_clients")
	public ResponseEntity<List<TypeClientDto>> listeTypeClt() {
		return new ResponseEntity<>(parametreService.fetchTypeClient(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_clients/page")
	public ResponseEntity<Page<TypeClient>> allpageTypeClient() {
		return new ResponseEntity<>(parametreService
				.findPageTypeClient(0, 5, "createdDate"), HttpStatus.OK);
	}


	@DeleteMapping("/type_clients/{id}")
	public ResponseEntity<Void> supprimertypeClient( @PathVariable Long id) {
		try {
			parametreService.deleteTypeClient(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * .
	 * creation des succursales
	 *
	 * @param dto the dto
	 *
	 * @return SuccursaleDto response entity
	 */
	@PostMapping(path = "/succursales")
	public ResponseEntity<SuccursaleDto> createSucc(
			@Valid @RequestBody final SuccursaleDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateSucc(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/succursales")
	public ResponseEntity<SuccursaleDto> updateSucc(
			@Valid @RequestBody final SuccursaleDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateSucc(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Liste succursale response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/succursales")
	public ResponseEntity<List<SuccursaleDto>> listeSuccursales() {
		return new ResponseEntity<>(parametreService.fetchSuccursales(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("succursales/page")
	public ResponseEntity<Page<Succursale>> allpageSucc() {
		return new ResponseEntity<>(parametreService
				.findPageSuccursale(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/succursales/{id}")
	public ResponseEntity<Void> supprimersucc( @PathVariable Long id) {
		try {
			parametreService.deleteSuccursale(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@GetMapping("/succursales/{idsucc}")
	public Optional<Succursale> findSuccursaleById(@PathVariable Long idsucc) {
		return parametreService.findSuccursaleById(idsucc);
	}

	/**
	 * .
	 * creation des fournisseurs
	 *
	 * @param dto the dto
	 *
	 * @return FournisseurDto response entity
	 */
	@PostMapping(path = "/fournisseurs")
	public ResponseEntity<FournisseurDto> createfssr(
			@Valid @RequestBody final FournisseurDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateFour(dto), HttpStatus.CREATED);
	}

	/**
	 * .
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PutMapping(path = "/fournisseurs")
	public ResponseEntity<FournisseurDto> updateFours(
			@Valid @RequestBody final FournisseurDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateFour(dto), HttpStatus.CREATED);
	}

	/**.
	 * Liste fournisseurs response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/fournisseurs")
	public ResponseEntity<List<FournisseurDto>> listeFournisseurs() {
		return new ResponseEntity<>(parametreService.fetchFournisseurs(), HttpStatus.OK);
	}

	/**
	 * supprimer un fournisseur
	 * @param id
	 * @return void
	 */
	@DeleteMapping("/fournisseurs/{id}")
	public ResponseEntity<Void> supprimerFournissseur( @PathVariable Long id) {
		try {
			parametreService.deleteFournisseur(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("fournisseurs/page")
	public ResponseEntity<Page<Fournisseur>> allpageFour() {
		return new ResponseEntity<>(parametreService
				.findPageFourn(0, 5, "createdDate"), HttpStatus.OK);
	}

	@GetMapping("/fournisseurs/{idFour}")
	public Optional<Fournisseur> getFournisseurById(@PathVariable Long idFour) {
		return parametreService.findFournisseurById(idFour);
	}


	/**.
	 * @param dto
	 * @return TaxeDto
	 */

	@PostMapping(path = "/taxes")
	public ResponseEntity<TaxeDto> createTaxe(
			@Valid @RequestBody final TaxeDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTaxes(dto), HttpStatus.CREATED);
	}

	/**.
	 * update taxes
	 * @param dto
	 * @return the response entity
	 */

	@PutMapping(path = "/taxes")
	public ResponseEntity<TaxeDto> updateTaxe(
			@Valid @RequestBody final TaxeDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTaxes(dto), HttpStatus.CREATED);
	}

	/**.
	 * retourne une taxe
	 * @return TaxeDto
	 */

	@GetMapping("/taxes")
	public ResponseEntity<List<TaxeDto>> listeTaxes() {
		return new ResponseEntity<>(parametreService.fetchTaxes(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("taxes/page")
	public ResponseEntity<Page<Taxe>> allpageTaxe() {
		return new ResponseEntity<>(parametreService
				.findPageTaxe(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/taxes/{id}")
	public ResponseEntity<Void> supprimertaxe( @PathVariable Long id) {
		try {
			parametreService.deleteTaxe(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**.
	 * @param dto
	 * @return TypeReglDto
	 */

	@PostMapping(path = "/type_reglements")
	public ResponseEntity<TypeReglDto> createTyperegl(
			@Valid @RequestBody final TypeReglDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeRegl(dto), HttpStatus.CREATED);
	}

	/**.
	 *
	 * @param dto
	 * @return the response entity
	 */

	@PutMapping(path = "/type_reglements")
	public ResponseEntity<TypeReglDto> update(
			@Valid @RequestBody final TypeReglDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeRegl(dto), HttpStatus.CREATED);
	}
	/**.
	 *
	 * @return  TypeReglDto
	 */

	@GetMapping("/type_reglements")
	public ResponseEntity<List<TypeReglDto>> liste() {
		return new ResponseEntity<>(parametreService.fetchTypeRegl(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_reglements/page")
	public ResponseEntity<Page<TypeReglement>> allpageTypeRegl() {
		return new ResponseEntity<>(parametreService
				.findPageTypeRegl(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/type_reglements/{id}")
	public ResponseEntity<Void> supprimerTyperegl( @PathVariable Long id) {
		try {
			parametreService.deleteTyperegl(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (BonDeCmdeFourNotFoundException | ProduitNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * mettra à jou les prix des produits
	 * @param id
	 * @param nouveauPrix
	 * @return produit
	 */
	@PutMapping("/update-prix/{id}")
		public ResponseEntity<Produit>updatePrix(
				@PathVariable Long id,
				@RequestParam BigDecimal nouveauPrix
				) {
			Produit produit = parametreService.updatePrixProd(id, nouveauPrix);
			return ResponseEntity.ok(produit);
		}

	/**
	 * mettre à jour les couts d'achat
	 * @param id
	 * @param nouveauCoutAchat
	 * @return produit
	 */

	@PutMapping("/update-cout-achat/{id}")
			public ResponseEntity<Produit> updateCoutAchat(
					@PathVariable Long id,
					@RequestParam BigDecimal nouveauCoutAchat) {
				Produit produit = parametreService.updateCoutProd(id, nouveauCoutAchat);
				return ResponseEntity.ok(produit);
			}

}
