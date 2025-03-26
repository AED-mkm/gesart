package org.gesart.gesart.domain.historique;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.parametrage.Produit;

import java.math.BigDecimal;


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