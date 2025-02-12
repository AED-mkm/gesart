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
@Table(name = "reglement")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Reglement extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_regl", sequenceName = "seq_regl",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "num_regl")
    private String numRegl;
    @Column(name = "date_regl")
    private LocalDate dateRegl;
    @Column(name = "montant_regl")
    private double montantRegl;
    @Column(name = "lib_regl")
    private String libelleRegl;
    @Column(name = "type_regl")
    private String typeRegl;
    /*@OneToMany(mappedBy = "reglment")
    private List<BordereauLivraison> bordereauLivraisons;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bon_cmd_fpur", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "reglement", allowSetters = true)
    private BonDeCmdeFour bonDeCmdeFour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "reglement", allowSetters = true)
    private Magasin magasin;

}