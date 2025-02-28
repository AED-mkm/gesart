package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.repository.admin.AbstractRepository;

import java.util.Optional;

public interface TaxeRepository extends AbstractRepository<Taxe, Long> {
 Optional<Taxe> findTaxeById(Long id);
}
