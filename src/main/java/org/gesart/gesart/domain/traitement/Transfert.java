package org.gesart.gesart.domain.traitement;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.parametrage.Magasin;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfert")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Transfert extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_sortie", sequenceName = "seq_sortie",
			initialValue = 8010, allocationSize = 5)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "magasin_source_id")
	private Magasin magasinSource;

	@ManyToOne
	@JoinColumn(name = "magasin_destination_id")
	private Magasin magasinDestination;

	private LocalDateTime dateTransfert;
	private String motifTransfert;

	@OneToMany(mappedBy = "transfert", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransfertProduit> transfertProduits;

}
