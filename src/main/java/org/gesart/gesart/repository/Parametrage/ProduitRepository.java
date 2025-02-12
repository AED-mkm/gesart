package org.gesart.gesart.repository.Parametrage;

import org.gesart.gesart.domain.Produit;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends AbstractRepository<Produit, Long> {
}
