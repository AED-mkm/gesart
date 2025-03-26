package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {




	Optional<Role> findByName(String name);
	Set<Role> findByNameIn(Set<String> names);
}
