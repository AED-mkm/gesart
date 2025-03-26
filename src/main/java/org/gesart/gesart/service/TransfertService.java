package org.gesart.gesart.service;

import org.gesart.gesart.domain.traitement.Transfert;
import org.gesart.gesart.dto.traitement.TransfertDto;

public interface TransfertService {

	TransfertDto effectuerTransfert(TransfertDto dto);
}
