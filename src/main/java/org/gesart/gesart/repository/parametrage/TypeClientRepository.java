package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.repository.admin.AbstractRepository;

import java.util.Optional;

public interface TypeClientRepository  extends AbstractRepository<TypeClient, Long> {

	Optional<TypeClient> findTypeClientById(Long id);

}
