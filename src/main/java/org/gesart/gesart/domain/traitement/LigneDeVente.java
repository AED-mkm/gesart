package org.gesart.gesart.domain.traitement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ligne_vente")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class LigneDeVente extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_prod", sequenceName = "seq_prod",
			initialValue = 8010, allocationSize = 5)
	private Long id;
	@Column(name = "qte_vente")
	@Positive
	private BigDecimal qteVente;
	@Column(name = "prix_unitaire")
	@Positive
	private BigDecimal prixUnitaire;
	@Column(name = "prix_total")
	private BigDecimal prixTotal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vente", referencedColumnName = "id")
	@JsonIgnoreProperties(value = "vente", allowSetters = true)
	private Vente vente;
}
