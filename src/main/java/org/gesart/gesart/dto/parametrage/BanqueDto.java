package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Succursale;
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
public class BanqueDto extends AbstractAuditEntityDto {
    private Long id;
    private String codeBanque;
    private String libellebanque;
    private String contact;
    private List<MagasinDto> magasins;
    private List<SuccursaleDto> succursales;
}