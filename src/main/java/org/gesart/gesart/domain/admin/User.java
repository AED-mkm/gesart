package org.gesart.gesart.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.config.SecurityConstants;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : <A>BRAHIMA TRAORE</A>
 * @version : 1.0
 * Copyright (c) 2024.
 * @since : 2024/12/15 à 00:16
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

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @Email
    @Size(min = 5, max = 254)
    @Pattern(regexp = SecurityConstants.LOGIN_REGEX)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Column(name = "lang_key", length = 10)
    private String langKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @Column(name = "has_photo")
    private Boolean hasPhoto;

    @Column(name = "user_image", length = 1024000)
    private byte[] photo;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @JsonIgnore
    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;

    @JsonIgnore
    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    /*@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profil_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Profil profil;*/
    @Transient
    private String info;

    /**
     * Constructor.
     *
     * @param id
     */
    public User(final Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mag_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Magasin magasin;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authoritySet;
    @ManyToMany
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

}
