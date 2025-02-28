package org.gesart.gesart.repository.parametrage;


import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.domain.parametrage.TypeReglement;
import org.gesart.gesart.repository.admin.AbstractRepository;

public interface TypeReglRepository extends AbstractRepository<TypeReglement, Long> {
	TypeReglement findTypeReglementById(Long id);
}
