package org.gesart.gesart.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.gesart.gesart.dto.admin.ProfilDto;
import org.gesart.gesart.service.admin.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;
import java.util.Optional;

@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfilResource {

    private final ProfileService profileService;

    /**
     * {@code POST  /profils} : Create a new profil.
     *
     * @param profilDTO the profilDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profilDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profils")
    @Operation(summary = "Endpoint permettant de creer un profil.",
            tags = {"profil", "post"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la création reussi"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<ProfilDto> createProfil(@Valid @RequestBody final ProfilDto profilDTO)
            throws URISyntaxException {
        log.debug("REST request to save Profil : {}", profilDTO);
        if (profilDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new profil cannot already have an ID");
        }
        ProfilDto result = profileService.save(profilDTO);
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
    @PutMapping("/profils")
    @Operation(summary = "Endpoint permettant de mettre a jour un profil.", tags = {"profil",
            "put"}, responses = {@ApiResponse(responseCode = "200", description = "Si la mise a jour reussi"),
            @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
            @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
            @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<ProfilDto> updateProfil(@Valid @RequestBody final ProfilDto profilDTO) {
        log.debug("REST request to update Profil : {}", profilDTO);
        if (profilDTO.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        ProfilDto result = profileService.save(profilDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /profils/:id} : get the "id" profil.
     *
     * @param id the id of the profilDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profilDTO.
     */
    @GetMapping("/profils/{id}")
    @Operation(summary = "Endpoint permettant de recuperer un profil à partir de son id.",
            tags = {"profil", "id", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la recuperation reussi"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<ProfilDto> getProfil(@PathVariable final Long id) {
        log.debug("REST request to get Profil : {}", id);
        Optional<ProfilDto> profilDTO = profileService.findOne(id);
        return ResponseEntity.ok(profilDTO.get());
    }

    /**
     * {@code DELETE  /profils/:id} : delete the "id" profil.
     *
     * @param id the id of the profilDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profils/{id}")
    @Operation(summary = "Endpoint permettant de supprimer un profil à partir de son id.",
            tags = {"profil", "id", "delete"},
            responses = {@ApiResponse(responseCode = "200", description = "Si la suppression reussi"),
                    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation"),
                    @ApiResponse(responseCode = "401", description = "Paramères de connexion incorrectes"),
                    @ApiResponse(responseCode = "500", description = "En cas d'erreur inattendue")})
    public ResponseEntity<Void> deleteProfil(@PathVariable final Long id) {
        log.debug("REST request to delete Profil : {}", id);
        profileService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
