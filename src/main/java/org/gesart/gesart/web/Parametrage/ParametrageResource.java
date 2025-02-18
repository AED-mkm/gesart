package org.gesart.gesart.web.Parametrage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
	public ResponseEntity<TaxeDto> updateFours(
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

}
