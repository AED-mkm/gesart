package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends AbstractRepository<Fournisseur, Long> {
}
