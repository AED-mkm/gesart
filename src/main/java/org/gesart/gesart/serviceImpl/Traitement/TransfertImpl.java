package org.gesart.gesart.serviceImpl.Traitement;


import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.Exception.MagasinNotFoundException;
import org.gesart.gesart.Exception.ProduitNotFoundException;
import org.gesart.gesart.Exception.StockInsuffisantException;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.Transfert;
import org.gesart.gesart.domain.traitement.TransfertProduit;
import org.gesart.gesart.dto.traitement.TransfertDto;
import org.gesart.gesart.dto.traitement.TransfertProduitDto;
import org.gesart.gesart.repository.parametrage.MagasinRepository;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.traitement.TransfertPrdRepository;
import org.gesart.gesart.repository.traitement.TransfertRepository;
import org.gesart.gesart.service.TransfertService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class TransfertImpl implements TransfertService {

	private final Mapper mapper;
  private TransfertRepository transfertRepository;

  private final TransfertPrdRepository transfertPrdRepository;

	private MagasinRepository magasinRepository;

	private ProduitRepository produitRepository;
	/**
	 * @param transfertDto
	 *
	 * @return
	 */
	@Override
		public TransfertDto effectuerTransfert(TransfertDto transfertDto) {
			// Validation des données
			Magasin magasinSource = magasinRepository.findById(transfertDto.getMagasinSourceId())
					.orElseThrow(() -> new MagasinNotFoundException("Magasin source introuvable"));
			Magasin magasinDestination = magasinRepository.findById(transfertDto.getMagasinDestinationId())
					.orElseThrow(() -> new MagasinNotFoundException("Magasin destination introuvable"));

			Transfert transfert = new Transfert();
			transfert.setMagasinSource(magasinSource);
			transfert.setMagasinDestination(magasinDestination);
			transfert.setDateTransfert(LocalDateTime.now());
			transfert.setMotifTransfert(transfertDto.getMotifTransfert());
			transfert = transfertRepository.save(transfert);

			List<TransfertProduit> transfertProduits = new ArrayList<>();
			for (TransfertProduitDto transfertProduitDto : transfertDto.getTransfertProduitDtos()) {
				Produit produit = produitRepository.findById(transfertProduitDto.getProduitId())
						.orElseThrow(() -> new ProduitNotFoundException("Produit introuvable"));

				// Vérification de la quantité en stock
				if (produit.getStockProduit().compareTo(transfertProduitDto.getQuantiteTransfert()) < 0 ) {
					throw new StockInsuffisantException("Stock insuffisant pour le produit " + produit.getDesignation());
				}

				TransfertProduit transfertProduit = new TransfertProduit();
				transfertProduit.setTransfert(transfert);
				transfertProduit.setProduit(produit);
				transfertProduit.setQuantiteTransfert(transfertProduitDto.getQuantiteTransfert());
				transfertPrdRepository.save(transfertProduit);
				transfertProduits.add(transfertProduit);

				// Mettre à jour les stocks
				produit.setStockProduit(produit.getStockProduit().subtract(transfertProduitDto.getQuantiteTransfert()));
				produitRepository.save(produit);
			}
			transfert.setTransfertProduits(transfertProduits);
			transfertRepository.save(transfert);
		 return mapper.map(transfert, TransfertDto.class);


		}

}
