package org.gesart.gesart.web.Traitement;

import lombok.RequiredArgsConstructor;
import org.gesart.gesart.Exception.MagasinNotFoundException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.Exception.StockInsuffisantException;
import org.gesart.gesart.domain.traitement.Transfert;
import org.gesart.gesart.dto.traitement.TransfertDto;
import org.gesart.gesart.service.TransfertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransfertController {

		private final TransfertService transfertService;
		@PostMapping("/transferts")
		public ResponseEntity<?> effectuerTransfert(@RequestBody TransfertDto transfertDto) {
			try {
				TransfertDto transfert = transfertService.effectuerTransfert(transfertDto);
				return ResponseEntity.ok(transfert);
			} catch (MagasinNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (ProduitNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (StockInsuffisantException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue s'est produite.");
			}
		}

}
