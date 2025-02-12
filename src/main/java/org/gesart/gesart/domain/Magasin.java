package org.gesart.gesart.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.util.List;

/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "magasin")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Magasin extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_mag", sequenceName = "seq_mag",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "code_mag")
    private String codeMagasin;
    @Column(name = "nom_mag")
    private String nomMagasin;
    @Column(name = "adresse_mag")
    private String adresseMagasin;
    @Column(name = "contact_mag")
    private String contactMagasin;
    @OneToMany(mappedBy = "magasin")
    private List<Produit> produits;

}