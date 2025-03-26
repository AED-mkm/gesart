package org.gesart.gesart.web.Traitement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gesart.gesart.dto.traitement.EntreDto;
import org.gesart.gesart.dto.traitement.VenteDto;
import org.gesart.gesart.service.EntreService;
import org.gesart.gesart.service.VenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VenteController {
	private final VenteService venteService;
	@PostMapping(path = "/vente")
	public ResponseEntity<VenteDto> createAndUpdateEntreMag(
			@Valid @RequestBody final VenteDto dto) {
		System.out.println("Données reçues : " + dto);
		return new ResponseEntity<>(venteService.createAndUpdateVenteMag(dto), HttpStatus.CREATED);
	}

	/*@GetMapping("/{id}")
	public ResponseEntity<EntreDto> getEntreeStock(@PathVariable Long id) {
		EntreDto entreeStock = entreService.getEntreeStock(id);
		return ResponseEntity.ok(entreeStock);
	}*/



}
