package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagasinRepository extends AbstractRepository<Magasin, Long> {
	Optional<Magasin> findMagasinById(Long id);
}
