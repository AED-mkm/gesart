package org.gesart.gesart.dto.traitement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.traitement.Transfert;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TransfertProduitDto extends AbstractAuditEntityDto {

	private Long id;
	private Transfert transfert;
	private Long produitId;
	private BigDecimal quantiteTransfert;



}
