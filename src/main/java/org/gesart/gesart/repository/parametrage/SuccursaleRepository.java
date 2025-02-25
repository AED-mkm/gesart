package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccursaleRepository extends AbstractRepository<Succursale, Long> {
}
