package org.gesart.gesart.repository.traitement;

import org.gesart.gesart.domain.traitement.TransfertProduit;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertPrdRepository extends AbstractRepository<TransfertProduit, Long> {
}