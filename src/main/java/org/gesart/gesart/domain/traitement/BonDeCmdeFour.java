package org.gesart.gesart.domain.traitement;




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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.admin.AbstractAuditEntity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourn_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "boncmdefour", allowSetters = true)
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "bonDeCmdeFour")
    private List<ProdBonCmdeFour> prodBonCmdeFours;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bon_cmde_four", allowSetters = true)
    private Magasin magasin;
    @Column(name = "total_cmde")
    private BigDecimal totalCmdeFour = BigDecimal.ZERO;
}
