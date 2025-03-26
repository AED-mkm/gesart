package org.gesart.gesart.repository.traitement;

import org.gesart.gesart.domain.traitement.Facture;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends AbstractRepository<Facture, Long> {


}