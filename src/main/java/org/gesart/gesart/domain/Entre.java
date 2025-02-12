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
@Table(name = "entre")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Entre extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_entre", sequenceName = "seq_entre",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "date_entre")
    private LocalDate dateEnt;
    @Column(name = "qte_entre")
    private int qteEntre;
    @Column(name = "objet_entre")
    private String objet;
    @Column(name = "prix_entre")
    private double prixEntre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourn_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "entree", allowSetters = true)
    private Fournisseur fournisseur;
   /* @OneToMany(mappedBy = "entre_id")
    private List<ProdBonCmdeFour> prodBonCmdeFours;*/
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mag_id", referencedColumnName = "id")
   @JsonIgnoreProperties(value = "entre", allowSetters = true)
   private Magasin magasin;

}