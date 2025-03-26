package org.gesart.gesart.service;



import org.gesart.gesart.dto.traitement.VenteDto;

import java.util.List;

public interface VenteService {
	VenteDto createAndUpdateVenteMag(VenteDto dto);
	List<VenteDto> fetchVente();




}
