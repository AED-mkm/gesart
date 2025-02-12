package org.gesart.gesart.domain;


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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.time.LocalDate;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sortie")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Sortie extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_sortie", sequenceName = "seq_sortie",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "date_vte")
    private LocalDate dateVente;
    @Column(name = "qte_vte")
    private int qteVente;
    @Column(name = "type_vte")
    private String typeVente;
    @Column(name = "objet")
    private String objet;
    @Column(name = "qte_dispo")
    private int qteDisponible;
    @Column(name = "prix_vte")
    private String prixVente;
   /* @OneToMany(mappedBy = "produit")
    private List<Produit> produits;*/
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mag_id", referencedColumnName = "id")
   @JsonIgnoreProperties(value = "sortie", allowSetters = true)
   private Magasin magasin;
}