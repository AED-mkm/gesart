package org.gesart.gesart.dto.traitement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EntreProduitDto extends AbstractAuditEntityDto {


	private Long produitId;
	private BigDecimal quantite;
	private BigDecimal prixEntre;


}
