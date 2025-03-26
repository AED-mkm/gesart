package org.gesart.gesart.domain.traitement;


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
@Table(name = "transfert_prod")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TransfertProduit extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_sortie", sequenceName = "seq_sortie",
			initialValue = 8010, allocationSize = 5)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "transfert_id")
	private Transfert transfert;

	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;

	private BigDecimal quantiteTransfert;
}
