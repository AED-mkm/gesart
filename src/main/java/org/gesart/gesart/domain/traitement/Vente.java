package org.gesart.gesart.domain.traitement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
import org.gesart.gesart.domain.enums.TypeVente;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Taxe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vente")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Vente extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_sortie", sequenceName = "seq_sortie",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "date_vte")
    private LocalDate dateVente;
    @Column(name = "type_vte")
    @Enumerated(EnumType.STRING)
    private TypeVente typeVente;
    @Column(name = "objet")
    private String objet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magasin", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "vente", allowSetters = true)
    private Magasin magasin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "vente", allowSetters = true)
    private Client client;
    @OneToMany(mappedBy = "vente")
    private List<LigneDeVente> lignesDeVente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taxe", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "vente", allowSetters = true)
    private Taxe taxe;
    @Column(name = "montant_ht")
    private BigDecimal montantHt = BigDecimal.ZERO;
    @Column(name = "montant_tva")
    private BigDecimal montantTva = BigDecimal.ZERO;
    @Column(name = "montant_bic")
    private BigDecimal montantBic = BigDecimal.ZERO;
    @Column(name = "montant_ttc")
    private BigDecimal montantTTC = BigDecimal.ZERO;
    private Long factureId;
}