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
public class ProduitDto {
    private Long id;
    private String numProduit;
    private String designation;
    private double prixProduit;
    private double stockProduit;
    private int nbElement;
    private double prixAchat;
    private double coutEmballage;
    private String typeEmballage;
    private MagasinDto magasin = new MagasinDto();
    private Long magasinId;
    private String libelleMag;

}