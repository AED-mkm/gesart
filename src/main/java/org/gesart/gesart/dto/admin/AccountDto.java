package org.gesart.gesart.dto.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gesart.gesart.config.SecurityConstants;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AccountDto {

    @NotNull(message = "Le nom d'utilisateur ne peut étre vide")
    @Size(min = 1, max = 50, message = "Le nom d'utilisateur ne peut étre vide")
    private String login;

    @NotNull(message = "Le champs est obligatoire")
    @Size(min = SecurityConstants.PASSWORD_MIN_LENGTH, max = SecurityConstants.PASSWORD_MAX_LENGTH,
            message = "Le mot de passe doit être d'au moins 4 carractères")
    private String password;

}
