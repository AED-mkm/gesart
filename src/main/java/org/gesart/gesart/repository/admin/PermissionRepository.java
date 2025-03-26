package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.admin.Permission;
import org.gesart.gesart.domain.admin.Profil;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
@SuppressWarnings("ALL")
@Repository
public interface PermissionRepository extends AbstractRepository<Permission, Long> {
	Optional<Permission> findByLibelle(String permissionName);
}
