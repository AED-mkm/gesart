package org.gesart.gesart.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.admin.Role;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PermissionDto extends AbstractAuditEntityDto {

    private Long id;
    private String libelle;
    private String description;
    /*private Role authority;*/
    private List<Role> roleList;


}
