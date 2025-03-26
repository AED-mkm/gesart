package org.gesart.gesart.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.dto.admin.PermissionDto;
import org.gesart.gesart.dto.admin.ProfilDto;
import org.gesart.gesart.service.admin.PermissionService;
import org.gesart.gesart.service.admin.ProfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PermissionResource {

    private final PermissionService permissionService;


    @PostMapping("/permissions")
    @Operation(summary = "Endpoint permettant de creer un profil.",
            tags = {"profil", "post"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la création reussi"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody final PermissionDto permissionDto)
            throws URISyntaxException {
        log.debug("REST request to save Profil : {}", permissionDto);
        if (permissionDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new profil cannot already have an ID");
        }
        PermissionDto result = permissionService.save(permissionDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /profils} : Updates an existing profil.
     *
     * @param profilDTO the profilDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profilDTO,
     * or with status {@code 400 (Bad Request)} if the profilDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profilDTO couldn't be updated.
     */
    @PutMapping("/permissions")
    @Operation(summary = "Endpoint permettant de mettre a jour un profil.", tags = {"profil",
            "put"}, responses = {@ApiResponse(responseCode = "200", description = "Si la mise a jour reussi"),
            @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
            @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<PermissionDto> updatePermissions(@Valid @RequestBody final PermissionDto permissionDto) {
        log.debug("REST request to update Profil : {}", permissionDto);
        if (permissionDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        PermissionDto result = permissionService.save(permissionDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/permissions/{id}")
    @Operation(summary = "Endpoint permettant de supprimer un profil à partir de son id.",
            tags = {"profil", "id", "delete"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la suppression reussi"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<Void> deletePermissions(@PathVariable final Long id) {
        log.debug("REST request to delete Profil : {}", id);
        permissionService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
