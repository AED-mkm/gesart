package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 *
 */

@Repository
@SuppressWarnings("ALL")
public interface UserRepository extends AbstractRepository<User, Long> {

    /**
     * Get user by email.
     *
     * @param email
     * @param statut
     * @return user
     */
    Optional<User> findOneByStatutAndEmailIgnoreCase(TypeStatut statut, String email);

    /**
     * Get user by login.
     *
     * @param login
     * @param statut
     * @return user
     */

    Optional<User> findOneByStatutAndLogin(TypeStatut statut, String login);

    /**
     * Get user by login and not activated.
     *
     * @param login
     * @param statut
     * @return user
     */
    Optional<User> findByStatutAndActivatedFalseAndLogin(TypeStatut statut, String login);


    /**
     * Recuperer les utilisateur non actives et créés avant la date donnée.
     *
     * @param dateTime
     * @param statut
     * @return {@link List <User>}
     */
    List<User> findAllByStatutAndActivatedIsFalseAndCreatedDateBefore(TypeStatut statut, Instant dateTime);


    /**
     * Get user by login.
     *
     * @param login
     * @param statut
     * @return user
     */
    Boolean existsByStatutAndLogin(TypeStatut statut, String login);

    /**
     * Get user by resetKey.
     *
     * @param resetKey
     * @param statut
     * @return Optional<User>
     */
    Optional<User> findByStatutAndResetKey(TypeStatut statut, String resetKey);

    /**
     * Get user by activationKey.
     *
     * @param activationKey
     * @param statut
     * @return Optional<User>
     */
    Optional<User> findByStatutAndActivationKey(TypeStatut statut, String activationKey);


}
