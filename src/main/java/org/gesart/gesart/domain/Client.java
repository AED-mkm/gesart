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



/**
 * @author Moctar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Client extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_client", sequenceName = "seq_client",
            initialValue = 8010, allocationSize = 5)
    private Long id;
    @Column(name = "code_client")
    private String codeClient;
    @Column(name = "denomination")
    private String denomination;
    @Column(name = "contact")
    private String contactClient;
    @Column(name = "adresse")
    private String adresseClient;
   /* @OneToMany(mappedBy = "client")
    private List<TypeClient> typeClients;*/
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mag_id", referencedColumnName = "id")
   @JsonIgnoreProperties(value = "client", allowSetters = true)
   private Magasin magasin;

}