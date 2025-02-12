package org.gesart.gesart.repository.Parametrage;

import org.gesart.gesart.domain.Client;
import org.gesart.gesart.repository.admin.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends AbstractRepository<Client, Long> {
}
