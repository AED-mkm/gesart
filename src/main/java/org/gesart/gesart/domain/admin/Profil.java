package org.gesart.gesart.domain.admin;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.HashSet;
import java.util.Set;


/**
 * @author Moctar
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "profil")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Profil extends AbstractAuditEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nt_profil_seq_generator")
    @SequenceGenerator(name = "nt_profil_seq_generator", sequenceName = "seq_nt_profil",
            initialValue = 1001, allocationSize = 2)
    private Long id;
    @Column(name = "libelle", nullable = false)
    private String libelle;
   /* @Column(name = "description")
    private String description;*/
//    @OneToMany(mappedBy = 'profil')
//    private List<Authority> authorities;
    @OneToMany(mappedBy = "profil")
    private Set<User> users = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profil_authority",
            joinColumns = {@JoinColumn(name = "profil_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

}