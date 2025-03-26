package org.gesart.gesart.repository.traitement;

import org.gesart.gesart.domain.traitement.LigneDeVente;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneVteRepository extends AbstractRepository<LigneDeVente, Long> {

	//Optional<EntreDto> findByIdWithProduits(Long id);
}