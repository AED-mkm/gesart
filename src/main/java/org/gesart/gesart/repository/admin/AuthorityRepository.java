package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.admin.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
