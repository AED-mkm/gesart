package org.gesart.gesart.domain.parametrage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@IdClass(NumeroSequentiel.NumeroSequentielId.class)
public class NumeroSequentiel {
	@Id
	private int annee;
	@Id
	private Long magasinId;

	private int numeroSequentiel;

	@Version
	private Long version;

	public NumeroSequentiel() {}

	public NumeroSequentiel(int annee, Long magasinId, int numeroSequentiel) {
		this.annee = annee;
		this.magasinId = magasinId;
		this.numeroSequentiel = numeroSequentiel;
	}

	public int getAnnee() { return annee; }
	public void setAnnee(int annee) { this.annee = annee; }

	public Long getMagasinId() { return magasinId; }
	public void setMagasinId(Long magasinId) { this.magasinId = magasinId; }

	public int getNumeroSequentiel() { return numeroSequentiel; }
	public void setNumeroSequentiel(int numeroSequentiel) { this.numeroSequentiel = numeroSequentiel; }

	public Long getVersion() { return version; }
	public void setVersion(Long version) { this.version = version; }

	public static class NumeroSequentielId implements Serializable {
		private int annee;
		private Long magasinId;

		public NumeroSequentielId() {}

		public NumeroSequentielId(int annee, Long magasinId) {
			this.annee = annee;
			this.magasinId = magasinId;
		}

		public int getAnnee() { return annee; }
		public void setAnnee(int annee) { this.annee = annee; }

		public Long getMagasinId() { return magasinId; }
		public void setMagasinId(Long magasinId) { this.magasinId = magasinId; }

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			NumeroSequentielId that = (NumeroSequentielId) o;
			return annee == that.annee && magasinId.equals(that.magasinId);
		}

		@Override
		public int hashCode() {
			return java.util.Objects.hash(annee, magasinId);
		}
	}
}

