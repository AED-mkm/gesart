package org.gesart.gesart.service;


import org.gesart.gesart.dto.traitement.EntreDto;


import java.util.List;

public interface EntreService {
	EntreDto createAndUpdateEntreMag(EntreDto dto);
	List<EntreDto> fetchEntre();


}
