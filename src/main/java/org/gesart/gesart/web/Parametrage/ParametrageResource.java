package org.gesart.gesart.web.Parametrage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gesart.gesart.domain.Banque;
import org.gesart.gesart.domain.Fournisseur;
import org.gesart.gesart.domain.Magasin;
import org.gesart.gesart.domain.Produit;
import org.gesart.gesart.domain.Succursale;
import org.gesart.gesart.domain.Taxe;
import org.gesart.gesart.domain.TypeClient;
import org.gesart.gesart.domain.TypeReglement;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.TaxeDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.dto.parametrage.TypeReglDto;
import org.gesart.gesart.service.Parametrage.ParametreService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * The type Parametrage resource.
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
	@GetMapping("magasin/page")
	public ResponseEntity<Page<Magasin>> allpage() {
		return new ResponseEntity<>(parametreService.findPageMagasin(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**
	 * .
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PostMapping(path = "/banque")
	public ResponseEntity<BanqueDto> createMag(
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
	@PutMapping(path = "/banque")
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

	@GetMapping("banque/page")
	public ResponseEntity<Page<Banque>> allpageBanque() {
		return new ResponseEntity<>(parametreService.findPageBanque(0, 5, "createdDate"), HttpStatus.OK);
	}


	/**
	 * .
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 *
	 * @return the response entity
	 */
	@PostMapping(path = "/produit")
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
	@PutMapping(path = "/produit")
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

	@GetMapping("produit/page")
	public ResponseEntity<Page<Produit>> allpageProduit() {
		return new ResponseEntity<>(parametreService.findPageProduit(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**
	 * .
	 * creation des clients
	 *
	 * @param dto the dto
	 *
	 * @return ClientDto response entity
	 */
	@PostMapping(path = "/client")
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
	@PutMapping(path = "/client")
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
	 * .
	 * creation de type client
	 *
	 * @param dto the dto
	 *
	 * @return TypeClientDto response entity
	 */
	@PostMapping(path = "/typeclient")
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
	@PutMapping(path = "/typeclient")
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
	@GetMapping("/typeclients")
	public ResponseEntity<List<TypeClientDto>> listeTypeClt() {
		return new ResponseEntity<>(parametreService.fetchTypeClient(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_client/page")
	public ResponseEntity<Page<TypeClient>> allpageTypeClient() {
		return new ResponseEntity<>(parametreService.findPageTypeClient(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**
	 * .
	 * creation des succursales
	 *
	 * @param dto the dto
	 *
	 * @return SuccursaleDto response entity
	 */
	@PostMapping(path = "/succursale")
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
	@PutMapping(path = "/succursale")
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
	@GetMapping("/succursale")
	public ResponseEntity<List<SuccursaleDto>> listeSuccursales() {
		return new ResponseEntity<>(parametreService.fetchSuccursales(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("succursale/page")
	public ResponseEntity<Page<Succursale>> allpageSucc() {
		return new ResponseEntity<>(parametreService.findPageSuccursale(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**
	 * .
	 * creation des fournisseurs
	 *
	 * @param dto the dto
	 *
	 * @return FournisseurDto response entity
	 */
	@PostMapping(path = "/fournisseur")
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
	@PutMapping(path = "/fournisseur")
	public ResponseEntity<FournisseurDto> updateFours(
			@Valid @RequestBody final FournisseurDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateFour(dto), HttpStatus.CREATED);
	}

	/**.
	 * Liste fournisseurs response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/fournisseur")
	public ResponseEntity<List<FournisseurDto>> listeFournisseurs() {
		return new ResponseEntity<>(parametreService.fetchFournisseurs(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("fournisseur/page")
	public ResponseEntity<Page<Fournisseur>> allpageFour() {
		return new ResponseEntity<>(parametreService.findPageFourn(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**.
	 * @param dto
	 * @return TaxeDto
	 */

	@PostMapping(path = "/taxe")
	public ResponseEntity<TaxeDto> createTaxe(
			@Valid @RequestBody final TaxeDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTaxes(dto), HttpStatus.CREATED);
	}

	/**.
	 * update taxes
	 * @param dto
	 * @return the response entity
	 */

	@PutMapping(path = "/taxe")
	public ResponseEntity<TaxeDto> updateTaxe(
			@Valid @RequestBody final TaxeDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTaxes(dto), HttpStatus.CREATED);
	}

	/**.
	 * retourne une taxe
	 * @return TaxeDto
	 */

	@GetMapping("/taxe")
	public ResponseEntity<List<TaxeDto>> listeTaxes() {
		return new ResponseEntity<>(parametreService.fetchTaxes(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("taxe/page")
	public ResponseEntity<Page<Taxe>> allpageTaxe() {
		return new ResponseEntity<>(parametreService.findPageTaxe(0, 5, "createdDate"), HttpStatus.OK);
	}

	/**.
	 * @param dto
	 * @return TypeReglDto
	 */

	@PostMapping(path = "/type_reglement")
	public ResponseEntity<TypeReglDto> createTyperegl(
			@Valid @RequestBody final TypeReglDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeRegl(dto), HttpStatus.CREATED);
	}

	/**.
	 *
	 * @param dto
	 * @return the response entity
	 */

	@PutMapping(path = "/type_reglement")
	public ResponseEntity<TypeReglDto> update(
			@Valid @RequestBody final TypeReglDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateTypeRegl(dto), HttpStatus.CREATED);
	}
	/**.
	 *
	 * @return  TypeReglDto
	 */

	@GetMapping("/type_reglement")
	public ResponseEntity<List<TypeReglDto>> liste() {
		return new ResponseEntity<>(parametreService.fetchTypeRegl(), HttpStatus.OK);
	}

	/**.
	 * Page
	 * @return Page
	 */

	@GetMapping("type_reglement/page")
	public ResponseEntity<Page<TypeReglement>> allpageTypeRegl() {
		return new ResponseEntity<>(parametreService.findPageTypeRegl(0, 5, "createdDate"), HttpStatus.OK);
	}

}
