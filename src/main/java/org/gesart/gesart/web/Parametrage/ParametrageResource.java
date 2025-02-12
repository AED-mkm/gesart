package org.gesart.gesart.web.Parametrage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
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
	 * Create mag response entity.
	 *
	 * @param dto the dto
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
	 * @return the response entity
	 */
	@PutMapping(path = "/magasins")
	public ResponseEntity<MagasinDto> updateMag(
			@Valid @RequestBody final MagasinDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateMg(dto), HttpStatus.CREATED);
	}

	/**
	 * Liste magasins response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/magasins")
	public ResponseEntity<List<MagasinDto>> listeMagasins() {
		return new ResponseEntity<>(parametreService.fetchMagasins(), HttpStatus.OK);
	}

	/**
	 * Create mag response entity.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PostMapping(path = "/banque")
	public ResponseEntity<BanqueDto> createMag(
			@Valid @RequestBody final BanqueDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateBanque(dto), HttpStatus.CREATED);
	}

	/**
	 * Update mag response entity.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PutMapping(path = "/banques")
	public ResponseEntity<BanqueDto> updateMag(
			@Valid @RequestBody final BanqueDto dto) {
		return new ResponseEntity<>(parametreService.createAndUpdateBanque(dto), HttpStatus.CREATED);
	}

	/**
	 * Liste magasins response entity.
	 *
	 * @return the response entity
	 */
	@GetMapping("/banques")
	public ResponseEntity<List<BanqueDto>> listeBanques() {
		return new ResponseEntity<>(parametreService.fetchBanques(), HttpStatus.OK);
	}

}
