package org.gesart.gesart.domain.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.security.Permissions;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "authority")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Role extends AbstractAuditEntity {
    @Id
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    @EqualsAndHashCode.Include
    private String name;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    /*@OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Permission> permissions;*/
    @ManyToMany()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, CascadeType.PERSIST})
    private List<Permission> permissionsList;


}
