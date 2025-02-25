package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanqueRepository extends AbstractRepository<Banque, Long> {
}
