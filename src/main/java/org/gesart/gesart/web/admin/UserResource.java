package org.gesart.gesart.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.config.SecurityConstants;
import org.gesart.gesart.domain.admin.Profil;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.dto.admin.KeyAndPasswordVM;
import org.gesart.gesart.dto.admin.PasswordChangedDto;
import org.gesart.gesart.dto.admin.UserDto;
import org.gesart.gesart.repository.admin.UserRepository;
import org.gesart.gesart.security.AuthoritiesConstants;
import org.gesart.gesart.service.admin.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {
    private final UserRepository userRepository;

    private final UserService userService;

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400
     * (Bad Request) if the login or email is already in use
     * @throws URISyntaxException      if the Location URI syntax is incorrect
     * @throws ResponseStatusException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/users/register")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @Operation(summary = "Endpoint permettant de creer un utilisateur.",
            tags = {"account", "login", "POST", "user", "register"},
            responses = {@ApiResponse(responseCode = "200", description = "Lorsque la création réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<Void> createUser(@Valid @RequestBody final UserDto userDTO) {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un nouveau utilisateur ne peut pas avoir un ID");
        } else if (userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF,
                userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Le nom d'utilisateur est déjà utilisé");
        } else {
            userService.registerUser(userDTO);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * PUT /users : Updates an existing User.
     *
     * @param userDTO the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user
     */
    @PutMapping("/users")
    @Operation(summary = "Endpoint permettant de mettre a jour un utilisateur.",
            tags = {"account", "login", "PUT", "user", "update"},
            responses = {@ApiResponse(responseCode = "200", description = "Lorsque la mise a jour réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<Void> updateUser(@Valid @RequestBody final UserDto userDTO) {
        log.debug("REST request to update User : {}", userDTO);
        Optional<User> existingUser = userRepository.findOneByStatutAndEmailIgnoreCase(TypeStatut.ACTIF,
                userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'adresse email est déjà utilisée");
        }
        existingUser = userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF,
                userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Le nom d'utilisateur est déjà utilisé");
        }
        userService.updateUser(userDTO);
        return ResponseEntity.ok().build();
    }


    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws ResponseStatusException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping(path = "/users/password")
    @Operation(summary = "Endpoint permettant de changer le mot de passe.", tags = {"account", "login",
            "POST", "user", "password", "update"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lorsque le changement du mot de passe reussie"),
            @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public void changePassword(@Valid @RequestBody final PasswordChangedDto passwordChangeDto) {
        if (checkPasswordLength(passwordChangeDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'adresse email est invalide");
        }
        userService.changePassword(passwordChangeDto);
    }


    /**
     *
     * @return UserDto
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @Operation(summary = "Endpoint pour recuperer l'enesemble des comptes utilisateurs.",
            tags = {"account", "GET", "users"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lorsque le changement du mot de passe reussie"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        final List<UserDto> page = userService.getAllManagedUsers();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     *
     * @return Profil
     */
    @GetMapping("/users/profils")
    // @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @Operation(summary = "Endpoint pour recuperer l'enesemble des profils des utilisateurs.", tags = {"account",
            "GET", "profils"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lorsque le changement du mot de passe reussie"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public List<Profil> getProfils() {
        return userService.getProfils();
    }

    /**
     *
     * @param login
     * @return UserDto
     */

    @GetMapping("/users/{login:" + SecurityConstants.LOGIN_REGEX + "}")
    @Operation(summary = "Endpoint pour recuperer un utilisateur a partir de son login.", tags = {"account",
            "GET", "user", "login"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lorsque le changement du mot de passe reussie"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<UserDto> getUser(@PathVariable final String login) {
        log.debug("REST request to get User : {}", login);
        return ResponseEntity.ok(userService.getUserWithRolesByLogin(login));
    }

    /**
     *
     * @param login
     * @return true or false
     */
    @DeleteMapping("/users/{login:" + SecurityConstants.LOGIN_REGEX + "}")
    /*    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")*/
    @Operation(summary = "Endpoint pour supprimer un utilisateur a partir de son login.", tags = {"account",
            "DELETE", "user", "login"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lorsque le changement du mot de passe reussie"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<Void> deleteUser(@PathVariable final String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUser(login);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    /**
     * Vérification du mot de passe.
     *
     * @param password
     * @return true or false
     */
    private static boolean checkPasswordLength(final String password) {
        return StringUtils.isEmpty(password)
                || password.length() < SecurityConstants.PASSWORD_MIN_LENGTH
                || password.length() > SecurityConstants.PASSWORD_MAX_LENGTH;
    }


    /**
     * Completer la modification du mot de passe.
     *
     * @param keyAndPassword
     * @return return true
     */
    @PostMapping(path = "/reset-password/finish")
    @Operation(summary = "Completer la modification du mot de passe", tags = {"User"})
    public ResponseEntity<Boolean> finishPasswordReset(@RequestBody final KeyAndPasswordVM keyAndPassword) {
        return new ResponseEntity<>(userService.finishPasswordReset(keyAndPassword), HttpStatus.OK);
    }

    /**
     * Completer la modification du mot de passe.
     *
     * @param keyAndPassword
     * @return return true
     */
    @PostMapping(path = "/activate-compte")
    @Operation(summary = "Completer la modification du mot de passe", tags = {"USer"})
    public ResponseEntity<Boolean> activateCompte(@RequestBody final KeyAndPasswordVM keyAndPassword) {
        return new ResponseEntity<>(userService.activateUser(keyAndPassword), HttpStatus.OK);
    }

    /**
     * User par id.
     *
     * @param id
     * @return UserDto
     */
    @GetMapping("/users/find-id")
    public ResponseEntity<UserDto> findUserById(@RequestParam final Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

}
