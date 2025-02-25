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
@Table(name = "hist_cout")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class HistoriqueCout extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_hist_cout", sequenceName = "seq_hist_cout",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "ncout_achat")
    private BigDecimal nouveauCoutAchat = BigDecimal.ZERO;
    @Column(name = "acout_achat")
    private BigDecimal ancienCoutAchat = BigDecimal.ZERO;
    @ManyToOne
    private Produit produit;
}