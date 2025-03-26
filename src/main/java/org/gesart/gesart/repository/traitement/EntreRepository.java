package org.gesart.gesart.repository.traitement;

import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntreRepository extends AbstractRepository<Entre, Long> {

	//Optional<EntreDto> findByIdWithProduits(Long id);
}