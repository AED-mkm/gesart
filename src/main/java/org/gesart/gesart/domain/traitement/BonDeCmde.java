package org.gesart.gesart.domain.traitement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
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
import org.gesart.gesart.domain.parametrage.Magasin;

import java.time.LocalDate;


/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bon_de_cmde")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BonDeCmde extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_bon_cmde", sequenceName = "seq_bon_cmde",
            initialValue = 8010, allocationSize = 5)
    private Long id;

    @Column(name = "date_bon_cmde")
    private LocalDate dateBonDeCmde;

    @Column(name = "type_bon_cmde")
    private String typeBonDeCmde;

    @Column(name = "prix_entre")
    private double prixEntreeBonDeCmde;

    @Column(name = "objet_boncmde")
    private String objetBonDeCmde;

    @NotNull
    @Column(name = "num_bon_cmde", nullable = false)
    private String numBonDeCmde;


 /*   @OneToMany(mappedBy = "bonDeCmdeFour")
    private List<ProdBonCmdeFour> prodBonCmdeFours;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bon_cmde", allowSetters = true)
    private Magasin magasin;

}