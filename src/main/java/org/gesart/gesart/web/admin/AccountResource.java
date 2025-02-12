package org.gesart.gesart.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Authority;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.dto.admin.AccountDto;
import org.gesart.gesart.dto.admin.CurrentUserDto;
import org.gesart.gesart.dto.admin.TokenDto;
import org.gesart.gesart.dto.admin.UserDto;
import org.gesart.gesart.repository.admin.UserRepository;
import org.gesart.gesart.security.jwt.TokenProvider;
import org.gesart.gesart.service.admin.AccountService;
import org.gesart.gesart.service.admin.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountResource {

    private final AccountService accountService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    /**
     * Connexion au système pour l'accès aux ressources.
     *
     * @param accountDto {@link AccountDto}
     * @return l'utilisateur connecté {@link UserDto}
     */
    @PostMapping(path = "users/login")
    @Operation(summary = "Endpoint permettant à un utilisateur de se connecter au système.", tags = {"account", "login",
            "post"}, responses = {@ApiResponse(responseCode = "200", description = "Si la connexion est une reussite"),
            @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
            @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<CurrentUserDto> login(@Valid @RequestBody final AccountDto accountDto) {
        User user = userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, accountDto.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ce compte n'existe pas ou a été désactivé !"));
        if (!user.isActivated()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Connexion refusée, votre compte n'est pas activé !");
        }
        return accountService.authorize(accountDto);
    }

    /**
     * Récuperation de l'utilisateur connecté.
     *
     * @return l'utilisateur connecté {@link UserDto}
     */
    @GetMapping("/users/account")
    @Operation(summary = "Endpoint permettant à un utilisateur de se connecter au système.",
            tags = {"account", "login", "post"},
            responses = {@ApiResponse(responseCode = "200", description = "Si s'il existe un utilisateur connecté"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'utilisateur n'est pas connecté"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<UserDto> getCurrentUser() {
        return new ResponseEntity<>(userService.getUserWithRoles(), HttpStatus.OK);
    }

    /**
     * Récuperation de la liste des rôles utilisateurs.
     *
     * @return list des rôles {@link List<Authority>}
     */
    @GetMapping("/users/authority")
    @Operation(summary = "Endpoint pour la récupération de la liste des droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la récupération réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou la liste n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<List<Authority>> getAllAuthorities() {
        return new ResponseEntity<>(accountService.getAllAuthority(), HttpStatus.OK);
    }

    /**
     * Création d'un rôle utilisateur.
     *
     * @param objet
     * @return d'un rôle {@link Authority}
     */
    @PostMapping("/users/authority")
    @Operation(summary = "Endpoint pour la creation d'un droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la création a réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'objet n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<Authority> createAuthority(@RequestBody final Authority objet) {
        return new ResponseEntity<>(accountService.createAuthority(objet), HttpStatus.OK);
    }

    /**
     * Suppression d'un rôle utilisateur.
     *
     * @param role
     * @return list d'un rôle {@link Authority}
     */
    @PatchMapping("/users/authority/{id}")
    @Operation(summary = "Endpoint pour la suppression d'un droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la suppression a réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'objet n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<Boolean> deleteAuthority(@RequestBody final Authority role) {
        return new ResponseEntity<>(accountService.suppressionAuthority(role.getName()), HttpStatus.OK);
    }

    /**
     * Vérification de la validter du token.
     *
     * @param token
     * @return Boolean
     */
    @PostMapping(path = "/verif-token-validite")
    public ResponseEntity<Boolean> validateJwtToken(@RequestBody final TokenDto token) {
        return null;
    }
}
