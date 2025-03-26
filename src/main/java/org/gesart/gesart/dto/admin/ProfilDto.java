package org.gesart.gesart.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.admin.Role;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProfilDto extends AbstractAuditEntityDto {

    private Long id;

    @NotNull(message = "Libelle est obligatoire.")
    private String libelle;

    private String description;

    @NotNull(message = "Rôle obligatoire.")
    private Set<Role> authorities = new HashSet<>();

}
