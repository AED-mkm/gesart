package org.gesart.gesart.domain.parametrage;

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
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produit")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Produit extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_prod", sequenceName = "seq_prod",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @NotNull
    @Column(name = "num_prod", unique = true)
    private String numProduit;
    @NotNull
    @Column(name = "designation", unique = true)
    private String designation;
    @NotNull
    @Column(name = "prix_actuel")
    private BigDecimal prixActuel = BigDecimal.ZERO;
    @Column(name = "ancien_prix")
    private BigDecimal ancienPrix = BigDecimal.ZERO;
    @Column(name = "prix_Maxi")
    private BigDecimal prixMax = BigDecimal.ZERO; // prix maxi du produit à ne pas depasser
    @Column(name = "stock")
    private BigDecimal stockProduit =  BigDecimal.ZERO;
    @Column(name = "nb_element")
    private int nbElement;
    @Column(name = "cout_achat")
    private BigDecimal coutAchat = BigDecimal.ZERO;
    @Column(name = "ancien_cout_achat")
    private BigDecimal ancienCoutAchat = BigDecimal.ZERO;
    @Column(name = "cout_embal")
    private BigDecimal coutEmballage = BigDecimal.ZERO;
    @Column(name = "type_embal")
    private String typeEmballage;
    @OneToMany(mappedBy = "produit")
    private List<ProdBonCmdeFour> prodBonCmdeFour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "produit", allowSetters = true)
    private Magasin magasin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entre", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prod_id", allowSetters = true)
    private Entre entre;
}