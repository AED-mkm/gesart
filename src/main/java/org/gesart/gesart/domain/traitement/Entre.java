package org.gesart.gesart.domain.traitement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    @Column(name = "objet_entre")
    private String objet;
    @Column(name = "num_bl")
    private String numBordLiv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourn_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "entree", allowSetters = true)
    private Fournisseur fournisseur;
  /*  @JsonIgnore
    @OneToMany(mappedBy = "entre_id")
    private List<ProdBonCmdeFour> prodBonCmdeFours;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "entre", allowSetters = true)
    private Magasin magasin;
   /* @ManyToMany(fetch = FetchType.LAZY)
    private List<Produit> produits;*/
   @OneToMany(mappedBy = "entre", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<EntreProduit> entreProduits = new ArrayList<>();

}