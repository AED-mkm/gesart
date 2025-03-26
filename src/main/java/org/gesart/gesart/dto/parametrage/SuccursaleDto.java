package org.gesart.gesart.dto.parametrage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.traitement.Operation;
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
public class SuccursaleDto extends AbstractAuditEntityDto {

    private Long id;
    private String codeSucc;
    private String libelleSucc;
    private String contactSucc;
    private Long banqueId;
    private String banqLibelle;
    private List<Operation> operations;
}