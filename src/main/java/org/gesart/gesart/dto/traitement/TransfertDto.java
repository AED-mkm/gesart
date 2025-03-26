package org.gesart.gesart.dto.traitement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TransfertDto extends AbstractAuditEntityDto {

	private Long id;
	private Long magasinSourceId;
	private Long magasinDestinationId;
	private String motifTransfert;
	private List<TransfertProduitDto> transfertProduitDtos;

}
