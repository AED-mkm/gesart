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
public class ClientDto extends AbstractAuditEntityDto {

    private Long id;
    private String codeClient;
    private String denomination;
    private String contactClient;
    private String adresseClient;
    private Long typeClientId;
    private Long magasinId;
    private String libelleMag;

}