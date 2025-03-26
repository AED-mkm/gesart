package org.gesart.gesart.domain.traitement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.parametrage.Produit;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entre_produit")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EntreProduit extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_entre_produit", sequenceName = "seq_entre_produit",
			initialValue = 8010, allocationSize = 5)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entre")
	private Entre entre;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produit")
	private Produit produit;

	private BigDecimal quantite;  // Quantité du produit pour cette entrée spécifique
	private BigDecimal prixEntre;


}
