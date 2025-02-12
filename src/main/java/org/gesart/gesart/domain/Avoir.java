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
@SuppressWarnings("ALL")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avoir")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Avoir extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_avoir", sequenceName = "seq_avoir",
            initialValue = 8010, allocationSize = 5)
    private Long id;

    @Column(name = "date_avoir")
    private LocalDate dateAvoir;

    @Column(name = "quantite_avoir")
    private double qteAvoir;

    @Column(name = "quantite_livrer")
    private int qteLiv;

    @Column(name = "etat_livr")
    private String etatLiv;

    @Column(name = "date_livr")
    private LocalDate dateLiv;

    @Column(name = "num_avoir")
    private String numAvoir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facture_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "avoirs", allowSetters = true)
    private Facture facture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bordLiv_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "avoirs", allowSetters = true)
    private BordereauLivraison bordereauLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "avoir", allowSetters = true)
    private Magasin magasin;

}