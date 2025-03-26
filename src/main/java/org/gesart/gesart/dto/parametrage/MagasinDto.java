package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.util.List;

/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class MagasinDto extends AbstractAuditEntityDto {
    private Long id;
    private String codeMagasin;
    private String nomMagasin;
    private String adresseMagasin;
    private String contactMagasin;
    private String responsableMag;
    private List<ProduitDto> produits;

}