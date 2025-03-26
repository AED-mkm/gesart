package org.gesart.gesart.dto.traitement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Moctar
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class VenteDto extends AbstractAuditEntityDto {
    private Long id;
    private LocalDate dateVente;
    private String typeVente;
    private String objet;
    private BigDecimal prixTotal;
    private Magasin magasin;
    private Long clientId;
    private String denomination;
    private List<LigneVenteDto> ligneVenteDtos;
    private List<String> taxesCochees;
    private BigDecimal montantHt;
    private BigDecimal montantTva;
    private BigDecimal montantBic;
    private BigDecimal montantTTC;
}