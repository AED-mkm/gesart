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
@Table(name = "banque")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Banque extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_banque", sequenceName = "seq_banque",
            initialValue = 8010, allocationSize = 5)

    private Long id;

    @Column(name = "code_banque")
    private String codeBanque;

    @Column(name = "banque")
    private String libellebanque;

    @Column(name = "contact")
    private String contact;

    /*@JsonIgnore
    @OneToMany(mappedBy = "banque")
    private List<Magasin> magasins;*/

    @OneToMany(mappedBy = "banque")
    private List<Succursale> succursales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "banque", allowSetters = true)
    private Magasin magasin;

}