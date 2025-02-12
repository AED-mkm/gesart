package org.gesart.gesart.repository.Parametrage;

import org.gesart.gesart.domain.Magasin;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagasinRepository extends AbstractRepository<Magasin, Long> {
}
