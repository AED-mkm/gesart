package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagasinRepository extends AbstractRepository<Magasin, Long> {
	Magasin findMagasinById(Long id);
}
