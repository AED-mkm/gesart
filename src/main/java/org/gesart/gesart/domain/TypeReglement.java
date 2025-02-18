package org.gesart.gesart.domain;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "code")
    private String code;
    @Column(name = "type_regl")
    private String typeRegl;
}