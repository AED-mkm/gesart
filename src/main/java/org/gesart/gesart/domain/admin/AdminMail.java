package org.gesart.gesart.domain.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author Moctar
 */
@SuppressWarnings("ALL")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "admin_mail")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AdminMail extends AbstractAuditEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_mail_seq_generator")
    @SequenceGenerator(name = "admin_mail_seq_generator", sequenceName = "seq_admin_mail",
            initialValue = 1010, allocationSize = 5)
    @Column(name = "id")
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "actif")
    private boolean actif;
}