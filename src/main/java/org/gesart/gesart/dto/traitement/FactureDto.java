package org.gesart.gesart.dto.traitement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.traitement.Vente;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.time.LocalDate;


/**
 * @author Moctar
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class FactureDto extends AbstractAuditEntityDto {


    private Long id;
    private String numFacture;
    private LocalDate dateFacture;
   /* @OneToMany(mappedBy = "facture")
    private List<Avoir> avoirs;
    @OneToMany(mappedBy = "facture")
    private List<MouvMag> mouvMags;*/
    private Magasin magasin;
    private Client client;
    private Vente vente;

}