package org.gesart.gesart.service.admin;

import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Profil;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.dto.admin.ProfilDto;
import org.gesart.gesart.repository.admin.ProfilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProfilService {

    private final ProfilRepository profilRepository;
    private final Mapper mapper;

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfilDto save(final ProfilDto profilDTO) {
        log.debug("Request to save Profil : {}", profilDTO);
        Profil profil = mapper.map(profilDTO, Profil.class);
        if (profil.getId() != null) {
            Profil prof = profilRepository.getOne(profil.getId());
            if (!prof.getAuthorities().isEmpty()) {
                prof.getAuthorities().clear();
                prof.setAuthorities(profilDTO.getAuthorities());
            }
        } else {
            profil.setStatut(TypeStatut.ACTIF);
        }
        log.debug("========================{}===================", profil);
        profil = profilRepository.save(profil);
        return mapper.map(profil, ProfilDto.class);
    }

    /**
     * Get all the profils.
     *
     * @param boutiqueId
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProfilDto> findAll(final Long boutiqueId) {
        log.debug("Request to get all Profils");
       /* Stream<Profil> profilStream = boutiqueId != null ? profilRepository.findByStatutAndBoutiqueId(
                TypeStatut.ACTIF, boutiqueId)
                : profilRepository.findAllByStatut(TypeStatut.ACTIF).stream();
        return profilStream.map(profil -> mapper.map(profil, ProfilDto.class)).collect(Collectors.toList());*/
        return null;
    }


    /**
     * Get one profil by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProfilDto> findOne(final Long id) {
        log.debug("Request to get Profil : {}", id);
        return profilRepository.findTop1ByStatutAndId(TypeStatut.ACTIF, id)
                .map(prof -> mapper.map(prof, ProfilDto.class));
    }

    /**
     * Delete the profil by id.
     *
     * @param id the id of the entity.
     */
    public void delete(final Long id) {
        log.debug("Request to delete Profil : {}", id);
        Profil profil = profilRepository.getOne(id);
        profil.getAuthorities().clear();
        profilRepository.delete(profil);
    }
}
