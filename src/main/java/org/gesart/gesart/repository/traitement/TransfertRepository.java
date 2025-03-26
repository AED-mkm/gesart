package org.gesart.gesart.repository.traitement;

import org.gesart.gesart.domain.traitement.Transfert;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends AbstractRepository<Transfert, Long> {
}