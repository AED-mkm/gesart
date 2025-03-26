package org.gesart.gesart.repository.traitement;


import org.gesart.gesart.domain.traitement.EntreProduit;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntreProduitRepository extends AbstractRepository<EntreProduit, Long> {
}
