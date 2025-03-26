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
import org.gesart.gesart.domain.admin.AbstractAuditEntity;
import org.gesart.gesart.domain.traitement.Operation;

import java.util.List;

/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "succursale")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Succursale extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_succ", sequenceName = "seq_succ",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "code_succ", unique = true)
    private String codeSucc;
    @NotBlank(message = "le libelle de la succ est obligatoire")
    @Column(name = "lib_succ")
    private String libelleSucc;
    @Column(name = "contact_succ")
    private String contactSucc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "succursales", allowSetters = true)
    private Banque banque;

    @OneToMany(mappedBy = "succursale")
    private List<Operation> operations;

    /*@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "succ", allowSetters = true)
    private Magasin magasin;*/
}