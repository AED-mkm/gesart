package org.gesart.gesart.repository.historique;

import org.gesart.gesart.domain.historique.HistoriquePrix;
import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriquePrixRepository extends AbstractRepository<HistoriquePrix, Long> {
}
