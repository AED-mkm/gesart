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
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.time.LocalDate;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mouv_mag")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class MouvMag extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_prod", sequenceName = "seq_prod",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "num_vehi")
    private String numvehicule;
    @Column(name = "nom_chauf")
    private String nomChauffeur;
    @Column(name = "num_bord")
    private String numBordereau;
    @Column(name = "sens")
    private String sens;
    @Column(name = "type_blf")
    private String typeBlf;
    @Column(name = "date_mouv")
    private LocalDate dateMouv;
    @Column(name = "provenance")
    private String provenance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facture_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "mouv_mag", allowSetters = true)
    private Facture facture;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bord_livr_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "mouv_mag", allowSetters = true)
    private BordereauLivraison bordereauLivraison;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "mouv_mag", allowSetters = true)
    private Magasin magasin;

}