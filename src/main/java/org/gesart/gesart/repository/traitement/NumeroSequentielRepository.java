
package org.gesart.gesart.repository.traitement;


import org.gesart.gesart.domain.parametrage.NumeroSequentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumeroSequentielRepository extends JpaRepository<NumeroSequentiel, NumeroSequentiel.NumeroSequentielId> {
	NumeroSequentiel findByAnneeAndMagasinId(int annee, Long magasinId);
	default void logRepositoryCreation() {
		System.out.println("NumeroSequentielRepository bean created");
	}
}

