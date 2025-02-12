package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ClientDto {

    private Long id;
    private String codeClient;
    private String denomination;
    private String contactClient;
    private String adresseClient;
   /* @OneToMany(mappedBy = "client")
    private List<TypeClient> typeClients;*/
   private MagasinDto magasin = new MagasinDto();
    private Long magasinId;
    private String libelleMag;

}