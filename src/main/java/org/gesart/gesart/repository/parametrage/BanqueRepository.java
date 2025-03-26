package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BanqueRepository extends AbstractRepository<Banque, Long> {
	Optional <Banque> findBanqueById(Long id);
}
