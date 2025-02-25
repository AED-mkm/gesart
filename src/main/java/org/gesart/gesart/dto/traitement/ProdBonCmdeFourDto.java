package org.gesart.gesart.dto.traitement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProdBonCmdeFourDto {
    private Long id;
    private BigDecimal qteProdCmde;
    private BigDecimal prixProdCmde;
    @Getter
    private BigDecimal montantProdCmde;
    private Long produitId;
    private String designation;
    private String numProduit;
    private Long bonDeCmdeFourId;
    private Long magasinId;
    private String libelleMag;
    public void calculerMontantProdCmde() {
        if (prixProdCmde != null && (qteProdCmde).compareTo(BigDecimal.ZERO) > 0) {
            this.montantProdCmde = prixProdCmde.multiply(qteProdCmde);
        }
    }
}
