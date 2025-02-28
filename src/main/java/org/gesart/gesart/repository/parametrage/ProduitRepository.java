package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends AbstractRepository<Produit, Long> {
	Optional <Produit> findProduitById(Long id);
}
