package org.gesart.gesart.dto.traitement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.dto.parametrage.ProduitDto;

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
public class EntreDto {
    private Long id;
    private LocalDate dateEnt;
    private BigDecimal qteEntre;
    private String objet;
    private BigDecimal prixEntre;
    private String numVehicule;
    private String nomChauffeur;
    private Long fournisseurId;
    private Long magasinId;
    private String responsableMag;
    private List<ProduitDto> produitDtos;


}