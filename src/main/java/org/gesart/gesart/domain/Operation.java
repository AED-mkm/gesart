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
@Table(name = "operation")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Operation extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_operation", sequenceName = "seq_operation",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "code_op")
    private String codeOp;
    @Column(name = "date_op")
    private LocalDate dateOP;
    @Column(name = "montant_op")
    private double montantOp;
    @Column(name = "sens_op")
    private String sensOp;
    @Column(name = "observ_op")
    private String observationOp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "succ_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "operation", allowSetters = true)
    private Succursale succursale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "operation", allowSetters = true)
    private Magasin magasin;


}