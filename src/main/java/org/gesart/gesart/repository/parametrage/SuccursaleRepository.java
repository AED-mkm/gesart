package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuccursaleRepository extends AbstractRepository<Succursale, Long> {
	Optional <Succursale> findSuccursaleById(Long id);
}
