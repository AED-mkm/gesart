package org.gesart.gesart.serviceImpl.Traitement;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.NumeroSequentiel;
import org.gesart.gesart.repository.traitement.NumeroSequentielRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureUtils {
	private static final Logger logger = LoggerFactory.getLogger(FactureUtils.class);

	@Autowired
	private NumeroSequentielRepository numeroSequentielRepository;

	private Long magasinId;

	@Transactional
	public String genererNumeroFacture() throws NumeroFactureException {
		try {
			int anneeCourante = LocalDate.now().getYear();
			logger.info("Récupération du numéro séquentiel pour l'année : {} et magasinId: {}", anneeCourante, magasinId);
			NumeroSequentiel numeroSequentiel = numeroSequentielRepository.findByAnneeAndMagasinId(anneeCourante, magasinId);
			if (numeroSequentiel == null) {
				logger.info("Aucun numéro séquentiel trouvé, création d'un nouveau");
				numeroSequentiel = new NumeroSequentiel(anneeCourante, magasinId, 0);
			}
			numeroSequentiel.setNumeroSequentiel(numeroSequentiel.getNumeroSequentiel() + 1);
			logger.info("Nouveau numéro séquentiel : {}", numeroSequentiel.getNumeroSequentiel());
			numeroSequentielRepository.save(numeroSequentiel);
			logger.info("Numéro séquentiel sauvegardé");
			String numeroSequentielFormatte = String.format("%07d", numeroSequentiel.getNumeroSequentiel());
			return "n° " + anneeCourante + " " + numeroSequentielFormatte;
		} catch (Exception e) {
			logger.error("Erreur lors de la génération du numéro de facture", e);
			throw new NumeroFactureException("Erreur lors de la génération du numéro de facture", e);
		}
	}
}

class NumeroFactureException extends Exception {
	public NumeroFactureException(String message, Throwable cause) {
		super(message, cause);
	}
}


