package org.gesart.gesart.dto.parametrage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;


/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TypeClientDto extends AbstractAuditEntityDto {
    private Long id;
    private String code;
    private String libelle;
    private Long magasinId;
    private String libelleMag;
}