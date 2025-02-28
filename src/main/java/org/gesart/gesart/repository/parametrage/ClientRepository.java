package org.gesart.gesart.repository.parametrage;

import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends AbstractRepository<Client, Long> {
	Client findClientById(Long id);
}
