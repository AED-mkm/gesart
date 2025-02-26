package org.gesart.gesart.domain.parametrage;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.traitement.BonDeCmdeFour;
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;

import java.util.List;

/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fournisseur")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Fournisseur extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_fourn", sequenceName = "seq_fourn",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "code_fourn", unique = true)
    private String codeFour;
    @NotBlank(message = "le nom du fournisseur est obligatoire")
    @Column(name = "nom_four")
    private String nomFour;
    @Column(name = "adresse_four")
    private String adresseFour;
    @Column(name = "contact_fourn")
    private String contactFour;
    @OneToMany(mappedBy = "fournisseur")
    private List<BonDeCmdeFour> bonDeCmdeFours;
    @OneToMany(mappedBy = "fournisseur")
    private List<Entre> entres;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "fournisseur", allowSetters = true)
    private Magasin magasin;
}