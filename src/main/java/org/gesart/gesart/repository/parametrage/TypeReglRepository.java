package org.gesart.gesart.repository.parametrage;


import org.gesart.gesart.domain.parametrage.TypeReglement;
import org.gesart.gesart.repository.admin.AbstractRepository;

import java.util.Optional;

public interface TypeReglRepository extends AbstractRepository<TypeReglement, Long> {
  Optional <TypeReglement> findTypeReglementById(Long id);
}
