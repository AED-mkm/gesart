package org.gesart.gesart.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.domain.admin.Permission;

import java.security.Permissions;
import java.util.List;


@Data

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RoleDto extends AbstractAuditEntityDto {

    @EqualsAndHashCode.Include
    private String name;
    private String description;
   /* private List<PermissionDto> permissions;*/
   private List<PermissionDto> permissionsList;
    private Long id;
}
