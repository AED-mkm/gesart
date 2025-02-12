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
import lombok.EqualsAndHashCode;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;



/**
 * @author Moctar
 */
@Entity
@Table(name = "prod_bon_cmde")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProdBonCmdeFour extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_prod_cmde", sequenceName = "seq_prod_cmde",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "qte_prod_cmde")
    private int qteProdCmde;
    @Column(name = "prix_prod_cmde")
    private double prixProdCmde;
    /*@OneToMany(mappedBy = "produit")
    private List<Produit> produits;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bon_cmde_four_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prod_bon_cmd_four", allowSetters = true)
    private BonDeCmdeFour bonDeCmdeFour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prod_bon_cmd_four", allowSetters = true)
    private Magasin magasin;
}