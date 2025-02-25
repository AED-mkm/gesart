package org.gesart.gesart.dto.traitement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.parametrage.Magasin;

import java.time.LocalDate;


/**
 * @author Moctar
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BonDeCmdeDto {


    private Long id;
    private LocalDate dateBonDeCmde;
    private String typeBonDeCmde;
    private double prixEntreeBonDeCmde;
    private String objetBonDeCmde;
    private String numBonDeCmde;
 /*   @OneToMany(mappedBy = "bonDeCmdeFour")
    private List<ProdBonCmdeFour> prodBonCmdeFours;*/
    private Magasin magasin;

}