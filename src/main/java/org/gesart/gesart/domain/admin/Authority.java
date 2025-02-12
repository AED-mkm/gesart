package org.gesart.gesart.domain.admin;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Moctar
 */
@SuppressWarnings("ALL")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "authority")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Authority extends AbstractAuditEntity {
    @Id
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    @EqualsAndHashCode.Include
    private String name;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;



}