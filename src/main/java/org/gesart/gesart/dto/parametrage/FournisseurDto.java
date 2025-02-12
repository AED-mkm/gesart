package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.BonDeCmdeFour;
import org.gesart.gesart.domain.Entre;


import java.util.List;

/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class FournisseurDto {

    private Long id;
    private String codeFour;
    private String nomFour;
    private String adresseFour;
    private String contactFour;
    private List<BonDeCmdeFour> bonDeCmdeFours;
    private List<Entre> entres;
    private MagasinDto magasin = new MagasinDto();
    private Long magasinId;
    private String libelleMag;
}