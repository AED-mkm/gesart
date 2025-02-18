package org.gesart.gesart.dto.admin;


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
import org.gesart.gesart.dto.parametrage.MagasinDto;


import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserDto {
    private Long id;

    @Pattern(regexp = "^[_.@A-Za-z0-9-]*$")
    @NotNull(message = "Le nom de l'utilisateur est obligatoire")
    @Size(min = 1, max = 50)
    private String login;

    @Size(min = SecurityConstants.PASSWORD_MIN_LENGTH, max = SecurityConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @Size(min = SecurityConstants.PASSWORD_MIN_LENGTH, max = SecurityConstants.PASSWORD_MAX_LENGTH)
    private String currentPassword;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private Instant createdDate;
    private String createdBy;

    private boolean activated = false;

    private boolean status = true;

    @Size(min = 2, max = 10)
    private String langKey;

  //  private byte[] photo;

    private String nom;

    private String prenom;

  //  private Boolean hasPhoto = Boolean.FALSE;

    private Long profilId;

    private String profilLibelle;

    private ProfilDto profil = new ProfilDto();

    private Boolean resetPassword = Boolean.FALSE;

   // private Boolean proprietaire = Boolean.FALSE;

    //private Long magasinId;

    private MagasinDto magasin = new MagasinDto();

    //private Long defaultMagasinId;

    private Boolean superAdmin = Boolean.FALSE;

    private boolean affectation = Boolean.FALSE;
}
