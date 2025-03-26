package org.gesart.gesart.service.admin;

import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Permission;
import org.gesart.gesart.dto.admin.PermissionDto;
import org.gesart.gesart.repository.admin.RoleRepository;
import org.gesart.gesart.repository.admin.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository authorityRepository;
    private final Mapper mapper;

    /**
     * Save a profil.
     *
     * @param permissionDto the entity to save.
     * @return the persisted entity.
     */
    public PermissionDto save(final PermissionDto permissionDto) {
        Permission permission = mapper.map(permissionDto, Permission.class);
        permission = permissionRepository.save(permission);
        return mapper.map(permission, PermissionDto.class);
    }



    /**
     * Delete the profil by id.
     *
     * @param id the id of the entity.
     */
    public void delete(final Long id) {
        log.debug("Request to delete Permission : {}", id);
        Permission permission = permissionRepository.getOne(id);
        permissionRepository.delete(permission);
    }
}
