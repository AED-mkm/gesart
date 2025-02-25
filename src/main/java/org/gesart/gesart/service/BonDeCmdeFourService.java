package org.gesart.gesart.service;

import org.gesart.gesart.dto.traitement.BonDeCmdeFourDto;
import org.gesart.gesart.dto.traitement.ProdBonCmdeFourDto;

import java.util.List;

public interface BonDeCmdeFourService {

	BonDeCmdeFourDto createAndUpdateBonDeCmdeFour(BonDeCmdeFourDto dto);

	List<BonDeCmdeFourDto> fetchBonCmdeFour();

	List<ProdBonCmdeFourDto> getProduitsByCommandeId(Long bonCmdeId);

	void supprimerProduit(Long id, Long prodId);

	void ajouterProduit(Long id, ProdBonCmdeFourDto prodDto);
}
