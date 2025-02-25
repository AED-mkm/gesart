package org.gesart.gesart.dto.parametrage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProduitDto {
    private Long id;
    private String numProduit;
    private String designation;
    private BigDecimal prixActuel;
    private BigDecimal ancienPrix;
    private BigDecimal stockProduit;
    private BigDecimal coutAchat;
    private BigDecimal ancienCoutAchat;
    private BigDecimal prixMax;
    private int nbElement;
    private BigDecimal coutEmballage;
    private String typeEmballage;
    private Long magasinId;
    private String libelleMag;
    private List<ProdBonCmdeFour> prodBonCmdeFour;
    private BigDecimal prixProdCmde;
    private Long entreId;


}