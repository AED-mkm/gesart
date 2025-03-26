package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.admin.AdminMail;

import java.util.Optional;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
public interface AdminMailRepository extends AbstractRepository<AdminMail, Long> {
    /**
     * find first by actif.
     *
     * @return Optional<AdminMail>
     */
    Optional<AdminMail> findFirstByActifTrue();
}
