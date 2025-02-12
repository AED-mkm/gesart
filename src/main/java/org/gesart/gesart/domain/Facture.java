package org.gesart.gesart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.time.LocalDate;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facture")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Facture extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_facture", sequenceName = "seq_facture",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "num_fact")
    private String numFacture;
    @Column(name = "date_fact")
    private LocalDate dateFacture;
   /* @OneToMany(mappedBy = "facture")
    private List<Avoir> avoirs;
    @OneToMany(mappedBy = "facture")
    private List<MouvMag> mouvMags;*/
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mag_id", referencedColumnName = "id")
   @JsonIgnoreProperties(value = "facture", allowSetters = true)
   private Magasin magasin;

}