package org.gesart.gesart.domain.admin;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import org.gesart.gesart.config.SecurityConstants;
import org.gesart.gesart.domain.parametrage.Magasin;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import jakarta.persistence.Table;


import java.time.Instant;


/**
 * @author Moctar
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
})
@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends AbstractAuditEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nt_user_seq_generator")
    @SequenceGenerator(name = "nt_user_seq_generator", sequenceName = "seq_nt_user",
            initialValue = 1001, allocationSize = 2)

    private Long id;
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenoms")
    private String prenoms;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @NotNull
    @Column(nullable = false)
    private boolean activated;

    @Email
    @Size(min = 5, max = 254)
    @Pattern(regexp = SecurityConstants.LOGIN_REGEX)
    @Column(length = 254, unique = true)
    private String email;

    @Column(name = "reset_date")
    private Instant resetDate;

    @JsonIgnore
    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;

    @JsonIgnore
    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Size(min = 2, max = 10)
    @Column(name = "lang_key", length = 10)
    private String langKey;

 /*   @OneToMany
    private List<Magasin> magasins;*/

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profil_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Profil profil;

    @Transient
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Magasin magasin;




}