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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bon_cmde_four")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BonDeCmdeFour extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_cmde_four", sequenceName = "seq_cmde_four",
            initialValue = 8010, allocationSize = 5)
    private Long id;

    @Column(name = "date_bon_cmde_four")
    private LocalDate dateBonDeCmdeFour;

    @Column(name = "lib_bon_cmde_four")
    private String libBonDeCmdeFour;
/*    @JsonIgnore
    @OneToMany(mappedBy = "bon_cmde_fourn")
    private List<BonDeCmde> bonDeCmdes;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourn_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "boncmdefour", allowSetters = true)
    private Fournisseur fournisseur;
    /*@OneToMany(mappedBy = "bon_cmde_four")
    private List<ProdBonCmdeFour> prodBonCmdeFours;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bon_cmde_four", allowSetters = true)
    private Magasin magasin;


}