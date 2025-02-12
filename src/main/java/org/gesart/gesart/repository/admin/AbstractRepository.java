package org.gesart.gesart.repository.admin;


import org.gesart.gesart.domain.enums.TypeStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * @param <ID>
 * @param <T>
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 */
@SuppressWarnings("ALL")
@NoRepositoryBean
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * Récupération de la liste des éléments non-supprimer.
     *
     * @param statut
     * @return la liste obtenue
     */
    List<T> findAllByStatut(TypeStatut statut);

    /**
     * Récupération d'un élément selon son Statut et son Id.
     *
     * @param id
     * @param statut
     * @return l'élément recherché
     */
    Optional<T> findTop1ByStatutAndId(TypeStatut statut, Long id);

    /**
     * Récupération d'un élément selon son Statut et son Id.
     *
     * @param id
     * @param statut
     * @return l'élément recherché
     */
    T findOneByStatutAndId(TypeStatut statut, Long id);
}
