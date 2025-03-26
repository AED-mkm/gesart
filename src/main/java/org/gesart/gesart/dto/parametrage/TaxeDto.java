package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.math.BigDecimal;


/**
 * @author Moctar
 */
@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TaxeDto extends AbstractAuditEntityDto {
    private Long id;
    private String code;
    private String libelle;
    private BigDecimal taxe;

}