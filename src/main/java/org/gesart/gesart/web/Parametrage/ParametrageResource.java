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
	//@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")

	@PostMapping(path = "/magasins")
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
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
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
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
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
	public ResponseEntity<List<MagasinDto>> listeMagasins() {
		return new ResponseEntity<>(parametreService.fetchMagasins(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return page
	 */


	@GetMapping("magasins/page")
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
	public ResponseEntity<Page<Magasin>> allpage() {
		return new ResponseEntity<>(parametreService
				.findPageMagasin(0, 5, "createdDate"), HttpStatus.OK);
	}


	@DeleteMapping("/magasins/{id}")
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
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
	@PreAuthorize("hasRole(T(org.gesart.gesart.security.AuthoritiesConstants).ADMIN)")
	public Optional<Magasin> geMagasinbyId(@PathVariable Long idMagasin) {
		return parametreService.findMagasinById(idMagasin);
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<BanqueDto>> listeBanques() {
		return new ResponseEntity<>(parametreService.fetchBanques(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("banques/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<Banque>> allpageBanque() {
		return new ResponseEntity<>(parametreService
				.findPageBanque(0, 5, "createdDate"), HttpStatus.OK);
	}
	@DeleteMapping("/banques/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<ProduitDto>> listeProduits() {
		return new ResponseEntity<>(parametreService.fetchProduits(), HttpStatus.OK);
	}
	/**.
	 * page
	 * @return page
	 */

	@GetMapping("produits/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<Produit>> allpageProduit() {
		return new ResponseEntity<>(parametreService
				.findPageProduit(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/produits/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Optional<Produit> getProduitById(@PathVariable Long idProd)
	{
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Client getClientById(@PathVariable Long idClient) {
		 return parametreService.findClientById(idClient);
	}

	@DeleteMapping("/clients/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<TypeClientDto>> listeTypeClt() {
		return new ResponseEntity<>(parametreService.fetchTypeClient(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_clients/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<TypeClient>> allpageTypeClient() {
		return new ResponseEntity<>(parametreService
				.findPageTypeClient(0, 5, "createdDate"), HttpStatus.OK);
	}


	@DeleteMapping("/type_clients/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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


	@GetMapping("/type_clients/{idTypeClt}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Optional<TypeClient> getTypeReglementById(@PathVariable Long idTypeClt) {
		return parametreService.findTypeClientById(idTypeClt);
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<SuccursaleDto>> listeSuccursales() {
		return new ResponseEntity<>(parametreService.fetchSuccursales(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("succursales/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<Succursale>> allpageSucc() {
		return new ResponseEntity<>(parametreService
				.findPageSuccursale(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/succursales/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<FournisseurDto>> listeFournisseurs() {
		return new ResponseEntity<>(parametreService.fetchFournisseurs(), HttpStatus.OK);
	}

	/**
	 * supprimer un fournisseur
	 * @param id
	 * @return void
	 */
	@DeleteMapping("/fournisseurs/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<Fournisseur>> allpageFour() {
		return new ResponseEntity<>(parametreService
				.findPageFourn(0, 5, "createdDate"), HttpStatus.OK);
	}

	@GetMapping("/fournisseurs/{idFour}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Optional<Fournisseur> getFournisseurById(@PathVariable Long idFour) {
		return parametreService.findFournisseurById(idFour);
	}


	/**.
	 * @param dto
	 * @return TaxeDto
	 */

	@PostMapping(path = "/taxes")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<TaxeDto> updateTaxe(
			@Valid @RequestBody final TaxeDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTaxes(dto), HttpStatus.CREATED);
	}

	/**.
	 * retourne une taxe
	 * @return TaxeDto
	 */

	@GetMapping("/taxes")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<TaxeDto>> listeTaxes() {
		return new ResponseEntity<>(parametreService.fetchTaxes(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("taxes/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<Taxe>> allpageTaxe() {
		return new ResponseEntity<>(parametreService
				.findPageTaxe(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/taxes/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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


	@GetMapping("/taxes/{idTaxe}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Optional<Taxe> getTaxeById(@PathVariable Long idTaxe) {
		return parametreService.findTaxeById(idTaxe);
	}

	/**.
	 * @param dto
	 * @return TypeReglDto
	 */

	@PostMapping(path = "/type_reglements")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<TypeReglDto> update(
			@Valid @RequestBody final TypeReglDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeRegl(dto), HttpStatus.CREATED);
	}
	/**.
	 *
	 * @return  TypeReglDto
	 */

	@GetMapping("/type_reglements")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<List<TypeReglDto>> liste() {
		return new ResponseEntity<>(parametreService.fetchTypeRegl(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_reglements/page")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public ResponseEntity<Page<TypeReglement>> allpageTypeRegl() {
		return new ResponseEntity<>(parametreService
				.findPageTypeRegl(0, 5, "createdDate"), HttpStatus.OK);
	}

	@DeleteMapping("/type_reglements/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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



	@GetMapping("/type_reglements/{idType}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
	public Optional<TypeReglement> findTypeReglementById(@PathVariable Long idType) {
		return parametreService.findTypeReglementById(idType);
	}

	/**
	 * mettra à jou les prix des produits
	 * @param id
	 * @param nouveauPrix
	 * @return produit
	 */
	@PutMapping("/update-prix/{id}")
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN)")
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
	@PreAuthorize("hasAuthority(T(org.gesart.gesart.security.AuthoritiesConstants).MAGASIN_ADMIN) " +
			" and @securityService.hasAccessToMagasin(#idMagasin))")
			public ResponseEntity<Produit> updateCoutAchat(
					@PathVariable Long id,
					@RequestParam BigDecimal nouveauCoutAchat) {
				Produit produit = parametreService.updateCoutProd(id, nouveauCoutAchat);
				return ResponseEntity.ok(produit);
			}

}
