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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bordereau_livr")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BordereauLivraison extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_bord_liv", sequenceName = "seq_bord_liv",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "num_bord")
    private String numBordereau;

    @Column(name = "date_bord")
    private LocalDate dateBordereau;

    @Column(name = "total_bord")
    private double totalBordereau;

    @Column(name = "total_general")
    private double totalGeneral;

    @Column(name = "total_remise")
    private double totalRemise;

    @Column(name = "net_a_payer")
    private double netApayer;

    @Column(name = "total_transport")
    private double totalTransport;

    @Column(name = "total_emballage")
    private double totalEmballage;

    @Column(name = "montant_payer")
    private double montantPayer;

    @Column(name = "taux_tva")
    private double tauxTva;

    @Column(name = "etat_bord")
    private String etatBordereau;

    @Column(name = "montant_tva")
    private double montantTva;

    @Column(name = "taux_bic")
    private int tauxBic;

    @Column(name = "montant_bic")
    private double montantBic;

    @Column(name = "montant_ttc")
    private double montantTtc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bord_liv", allowSetters = true)
    private Client client;

    /*@OneToMany(mappedBy = "bord_liv")
    private List<Avoir> avoirs;
    @OneToMany(mappedBy = "bord_livr")
    private List<ProduitBordLiv> produitBordLivs;
    @OneToMany(mappedBy = "bord_livr")
    private List<MouvMag> mouvMags;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bord_liv", allowSetters = true)
    private Magasin magasin;
}