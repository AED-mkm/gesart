package org.gesart.gesart.domain.traitement;

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
import org.gesart.gesart.domain.parametrage.Magasin;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prod_bord_liv")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProduitBordLiv extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_prod", sequenceName = "seq_prod",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "qte_bord_liv")
    private int qteBordLiv;
    @Column(name = "prix_bord_liv")
    private double prixBordLiv;
    @Column(name = "prix_achat_bord")
    private double prixAchatBordLiv;
    /*@OneToMany(mappedBy = "prod_bord_liv")
    private List<Produit> produits;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bord_liv_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prod_bord_liv", allowSetters = true)
    private BordereauLivraison bordereauLivraison;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prod_bord_liv", allowSetters = true)
    private Magasin magasin;

}