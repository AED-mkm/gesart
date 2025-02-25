package org.gesart.gesart.repository.historique;

import org.gesart.gesart.domain.historique.HistoriqueCout;
import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueCoutRepository extends AbstractRepository<HistoriqueCout, Long> {
}
