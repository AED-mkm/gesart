package org.gesart.gesart.domain.parametrage;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;


/**
 * @author Moctar
 */
@SuppressWarnings("ALL")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_regl")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TypeReglement extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_type_regl", sequenceName = "seq_type_regl",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @NotBlank(message = "le code de type client est obligatoire")
    @Column(name = "code",unique = true)
    private String code;
    @NotBlank(message = "le de type reglement est obligatoire")
    @Column(name = "type_regl", unique = true)
    private String typeRegl;
}