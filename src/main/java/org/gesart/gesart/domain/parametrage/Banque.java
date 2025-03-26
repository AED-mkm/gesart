package org.gesart.gesart.domain.parametrage;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "banque")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Banque extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_banque", sequenceName = "seq_banque",
            initialValue = 8010, allocationSize = 5)

    private Long id;
    @NotBlank(message = "le code banque est obligatoire")
    @Column(name = "code_banque" , unique = true)
    private String codeBanque;
    @NotBlank(message = "le nom de la banque est obligatoire")
    @Column(name = "banque")
    private String libellebanque;
    @Column(name = "contact")
    private String contact;
    @JsonIgnore
    @OneToMany(mappedBy = "banque")
    private List<Succursale> succursales;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Magasin> magasins;

}