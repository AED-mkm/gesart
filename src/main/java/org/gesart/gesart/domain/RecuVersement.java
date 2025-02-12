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
@Table(name = "recu_vers")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RecuVersement extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_recu", sequenceName = "seq_recu",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "num_recu")
    private String numRecu;
    @Column(name = "date_recu")
    private LocalDate dateRecu;
    @Column(name = "montant")
    private double montantRecu;
    @Column(name = "libelle")
    private String libelleRecu;
    @Column(name = "taux_tva")
    private double tauxTva;
    @Column(name = "montant_tva")
    private double montantTva;
    @Column(name = "taux_bic")
    private double tauxBic;
    @Column(name = "montant_bic")
    private double montantBic;
    @Column(name = "montant_ttc")
    private double montantTtc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "recu", allowSetters = true)
    private Client client;
    /*@OneToMany(mappedBy = "recu")
    private List<Facture> factures;
    @OneToMany(mappedBy = "recu")
    private List<Avoir> avoirs;
    @OneToMany(mappedBy = "recu")
    private List<BordereauLivraison> bordereauLivraisons;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "recu", allowSetters = true)
    private Magasin magasin;
}