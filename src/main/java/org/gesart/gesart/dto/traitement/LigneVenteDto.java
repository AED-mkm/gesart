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
public class LigneVenteDto extends AbstractAuditEntityDto {

	private Long id;
	private BigDecimal qteVente;
	private BigDecimal prixUnitaire;
	private BigDecimal prixTotal;
	private Long produitId;
	private Long venteId;
}
