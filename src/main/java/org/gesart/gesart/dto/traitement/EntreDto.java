package org.gesart.gesart.dto.traitement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.EntreProduit;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.dto.admin.AbstractAuditEntityDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Moctar
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EntreDto extends AbstractAuditEntityDto {
    private Long id;
    private LocalDate dateEnt;
    private String objet;
    private String numBordLiv;
    @NotNull
    private Long fournisseurId;
    private List<EntreProduitDto> entreProduitsDto;


}