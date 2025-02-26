package org.gesart.gesart.domain.parametrage;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.math.BigDecimal;


/**
 * @author Moctar
 */
@SuppressWarnings("ALL")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taxe")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Taxe extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_taxe", sequenceName = "seq_taxe",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @NotBlank(message = "le code de la taxe est obligatoire")
    @Column(name = "code", unique = true)
    private String code;
    @NotBlank(message = "le libelle de la taxe est obligatoire")
    @Column(name = "libelle", unique = true)
    private String libelle;
    @NotNull
    @Column(name = "taxe")
    private BigDecimal taxe;

}