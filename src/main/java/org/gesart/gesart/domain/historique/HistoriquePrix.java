package org.gesart.gesart.domain.historique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hist_prix")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class HistoriquePrix extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_hist_prix", sequenceName = "seq_hist_prix",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "nouveau_prix")
    private BigDecimal nouveauPrix = BigDecimal.ZERO;
    @Column(name = "ancien_prix")
    private BigDecimal ancienPrix = BigDecimal.ZERO;
    @ManyToOne
    private Produit produit;
}