package org.gesart.gesart.domain.parametrage;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.traitement.Vente;

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
    @Column(name = "code_mag", unique = true)
    private String codeMagasin;
    @NotBlank(message = "le nom du magasin est obligatoire")
    @Column(name = "nom_mag")
    private String nomMagasin;
    @Column(name = "adresse_mag")
    private String adresseMagasin;
    @Column(name = "contact_mag")
    private String contactMagasin;
    @Column(name = "responsable")
    private String responsableMag;
    @JsonIgnore
    @OneToMany(mappedBy = "magasin")
    private List<Produit> produits;
    @JsonIgnore
    @OneToMany(mappedBy = "magasin")
    private List<Fournisseur> fournisseurs;
    @JsonIgnore
    @OneToMany(mappedBy = "magasin")
    private List<Vente> ventes;
    @JsonIgnore
    @OneToMany(mappedBy = "magasin")
    private List<User> users;
}