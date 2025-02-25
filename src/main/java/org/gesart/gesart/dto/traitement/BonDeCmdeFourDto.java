package org.gesart.gesart.dto.traitement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BonDeCmdeFourDto {
    private Long id;
    private LocalDate dateBonDeCmdeFour;
    private String libBonDeCmdeFour;
    private Long fournisseurId;
    private String nomFour;
    private List<ProdBonCmdeFourDto> prodBonCmdeFourDto;
    private Long magasinId;
    private String libelleMag;
    private BigDecimal totalCmdeFour;


}