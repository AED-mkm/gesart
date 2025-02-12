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
    @Column(name = "num_prod")
    private String numProduit;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prix_produit")
    private double prixProduit;
    @Column(name = "stock")
    private double stockProduit;
    @Column(name = "nb_element")
    private int nbElement;
    @Column(name = "prix_achat")
    private double prixAchat;
    @Column(name = "cout_embal")
    private double coutEmballage;
    @Column(name = "type_embal")
    private String typeEmballage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "produit", allowSetters = true)
    private Magasin magasin;

}