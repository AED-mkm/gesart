package org.gesart.gesart.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Role;
import org.gesart.gesart.dto.admin.RoleDto;
import org.gesart.gesart.repository.admin.UserRepository;
import org.gesart.gesart.security.jwt.TokenProvider;
import org.gesart.gesart.service.admin.AccountService;
import org.gesart.gesart.service.admin.RoleService;
import org.gesart.gesart.service.admin.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleResource {

    private final AccountService accountService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final RoleService authorityService;


    /**
     * Récuperation de la liste des rôles utilisateurs.
     *
     * @return list des rôles {@link List< Role >}
     */
    @GetMapping("/roles")
    @Operation(summary = "Endpoint pour la récupération de la liste des droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la récupération réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou la liste n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<List<Role>> getAllAuthorities() {
        return new ResponseEntity<>(accountService.getAllAuthority(), HttpStatus.OK);
    }

    /**
     * Création d'un rôle utilisateur.
     *
     * @param objet
     * @return d'un rôle {@link Role}
     */
    @PostMapping("/roles")
    @Operation(summary = "Endpoint pour la creation d'un droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la création a réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'objet n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<RoleDto> createAuthority(@RequestBody final RoleDto roleDto) {
        return new ResponseEntity<>(authorityService.createAndUpdateRole(roleDto), HttpStatus.OK);
    }

    @PutMapping("/roles")
    @Operation(summary = "Endpoint pour la creation d'un droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la création a réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'objet n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<RoleDto> modifierUnRole(@RequestBody final RoleDto roleDto) {
        return new ResponseEntity<>(authorityService.createAndUpdateRole(roleDto), HttpStatus.OK);
    }

    /**
     * retourne un role à partir de son nom
     * @param authorityName
     * @return authorityDto
     */


    @GetMapping("/users/{authorityName}")
    public ResponseEntity<Optional<Role>> getAuthorityByName(@PathVariable String authorityName) {
        log.debug("REST request to get Authority by name : {}", authorityName);
        Optional<Role> authorityDto = authorityService.findByName(authorityName);
        return ResponseEntity.ok(authorityDto);
    }

    /**
     * Suppression d'un rôle utilisateur.
     *
     * @param role
     * @return list d'un rôle {@link Role}
     */
    @PatchMapping("/roles/{id}")
    @Operation(summary = "Endpoint pour la suppression d'un droits d'accès",
            tags = {"authority", "user", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la suppression a réussie"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation des accès"),
                    @ApiResponse(responseCode = "404", description = "Au cas ou l'objet n'existe pas"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    private ResponseEntity<Boolean> deleteAuthority(@RequestBody final Role role) {
        return new ResponseEntity<>(accountService.suppressionAuthority(role.getName()), HttpStatus.OK);
    }

}
