package org.gesart.gesart.service.admin;

import com.github.dozermapper.core.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Role;
import org.gesart.gesart.domain.admin.Permission;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.dto.admin.RoleDto;
import org.gesart.gesart.repository.admin.RoleRepository;
import org.gesart.gesart.repository.admin.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository authorityRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    @Transactional
    public RoleDto createAndUpdateRole(final RoleDto dto) {
        Role role = mapper.map(dto, Role.class);
        if(role.getPermissionsList()!=null){
            List<Permission> permission = role.getPermissionsList()
                    .stream()
                    .map(permissionDto ->mapper.map(permissionDto, Permission.class))
                    .collect(Collectors.toList());
            permission = permissionRepository.saveAll(permission);
        }
        roleRepository.save(role);
        return mapper.map(role, RoleDto.class);
    }

    /**
     *
     * @param authorityName
     * @param libelle
     * @return
     */

    public RoleDto ajouterPermissionAuRole(final String authorityName, final String libelle) {
        log.debug("Request to add Permission {} to Role {}", libelle, authorityName);

        // Récupérer le rôle par nom
        Role role = roleRepository.findByName(authorityName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name: " + authorityName));

        // Récupérer la permission par nom
        Permission permission = permissionRepository.findByLibelle(libelle)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with name: " + libelle));

        // Vérifier si la permission est déjà associée au rôle
        if (!role.getPermissionsList().contains(permission)) {
            role.getPermissionsList().add(permission);
            roleRepository.save(role);
            log.info("Permission {} added to Role {}", libelle, authorityName);
        } else {
            log.warn("Permission {} is already associated with Role {}", libelle, authorityName);
        }

        return mapper.map(role, RoleDto.class);
    }




    public Optional<Role> findByName(String authorityName) {
        return authorityRepository.findByName(authorityName);
    }


}
