package org.gesart.gesart.service.Parametrage;


import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.Client;
import org.gesart.gesart.domain.Banque;
import org.gesart.gesart.domain.Produit;
import org.gesart.gesart.domain.Magasin;
import org.gesart.gesart.domain.Succursale;
import org.gesart.gesart.domain.TypeClient;
import org.gesart.gesart.domain.Fournisseur;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.organisation.ParamsInt;
import org.gesart.gesart.repository.Parametrage.SuccursaleRepository;
import org.gesart.gesart.repository.Parametrage.ClientRepository;
import org.gesart.gesart.repository.Parametrage.FournisseurRepository;
import org.gesart.gesart.repository.Parametrage.TypeClientRepository;
import org.gesart.gesart.repository.Parametrage.BanqueRepository;
import org.gesart.gesart.repository.Parametrage.MagasinRepository;
import org.gesart.gesart.repository.Parametrage.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class ParametreService implements ParamsInt {
	private final Mapper mapper;
	private final ClientRepository clientRepository;
	private final BanqueRepository banqueRepository;
	private final FournisseurRepository fournisseurRepository;
	private final MagasinRepository magasinRepository;
	private final ProduitRepository produitRepository;
	private final SuccursaleRepository succursaleRepository;
	private final TypeClientRepository typeClientRepository;

	/**
	 * .
	 * creation et mise à jour des client
	 *
	 * @param dto
	 * @return ClientDto
	 */
	@Override
	public ClientDto createAndUpdateClient(final ClientDto dto) {
		Client entity = mapper.map(dto, Client.class);
		entity = clientRepository.save(entity);
		return mapper.map(entity, ClientDto.class);
	}

	/**
	 * .
	 * supprimer un client
	 *
	 * @param id
	 */
	@Override
	public void deleteClient(final Long id) {
		if (id == null) {
			log.error("aucun client trouvé!");
			return;
		}
		clientRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des clients
	 *
	 * @return List<ClientDto>
	 */
	@Override
	public List<ClientDto> fetchClients() {
		return clientRepository.findAll().stream().map(client ->
				mapper.map(client, ClientDto.class)).collect(Collectors.toList());
	}


	/**
	 * .
	 * Liste des clients par page
	 *
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return PosteDto
	 */
	@Override
	public Page<Client> findPage(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return clientRepository.findAll(pageable);
	}

	/**
	 * .
	 * creation et mise à jour des banque
	 *
	 * @param dto
	 * @return BanqueDto
	 */
	@Override
	public BanqueDto createAndUpdateBanque(final BanqueDto dto) {
		Banque entity = mapper.map(dto, Banque.class);
		entity = banqueRepository.save(entity);
		return mapper.map(entity, BanqueDto.class);
	}

	/**
	 * .
	 * supprimer un client
	 *
	 * @param id
	 */
	@Override
	public void deleteBanque(final Long id) {
		if (id == null) {
			log.error("Aucune banque avec cet id");
			return;
		}
		banqueRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des clients
	 *
	 * @return List<BanqueDto>
	 */
	@Override
	public List<BanqueDto> fetchBanques() {
		return banqueRepository.findAll().stream().map(banque ->
				mapper.map(banque, BanqueDto.class)).collect(Collectors.toList());
	}

	/**
	 * .
	 * creation et mise à jour des fournisseurs
	 *
	 * @param dto
	 * @return FournisseurDto
	 */
	@Override
	public FournisseurDto createAndUpdateFour(final FournisseurDto dto) {
		Fournisseur entity = mapper.map(dto, Fournisseur.class);
		entity = fournisseurRepository.save(entity);
		return mapper.map(entity, FournisseurDto.class);
	}

	/**
	 * .
	 * supprimer un fournisseur
	 *
	 * @param id
	 */
	@Override
	public void deleteFournisseure(final Long id) {
		if (id == null) {
			log.error("Aucun fournisseur avec ce id");
			return;
		}
		fournisseurRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des fournisseurs
	 *
	 * @return List<FournisseurDto>
	 */
	@Override
	public List<FournisseurDto> fetchFournisseurs() {
		return fournisseurRepository.findAll().stream().map(fournisseur ->
				mapper.map(fournisseur, FournisseurDto.class)).collect(Collectors.toList());
	}

	/**
	 * .
	 *
	 * @param dto
	 * @return MagasinDto
	 */
	@Override
	public MagasinDto createAndUpdateMg(final MagasinDto dto) {
		Magasin entity = mapper.map(dto, Magasin.class);
		entity = magasinRepository.save(entity);
		log.info(entity.toString());
		return mapper.map(entity, MagasinDto.class);
	}

	/**
	 * .
	 * supprimer un fournisseur
	 *
	 * @param id
	 */
	@Override
	public void deleteMagasin(final Long id) {
		if (id == null) {
			log.error("Ce magasin n'existe pas!");
			return;
		}
		magasinRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des magasins
	 *
	 * @return List<MagasinDto>
	 */
	@Override
	public List<MagasinDto> fetchMagasins() {
		return magasinRepository.findAll().stream().map(magasin ->
				mapper.map(magasin, MagasinDto.class)).collect(Collectors.toList());
	}

	/**
	 * .
	 *
	 * @param dto
	 * @return ProduitDto
	 */
	@Override
	public ProduitDto createAndUpdateProd(final ProduitDto dto) {
		Produit entity = mapper.map(dto, Produit.class);
		entity = produitRepository.save(entity);
		return mapper.map(entity, ProduitDto.class);
	}

	/**
	 * .
	 * supprimer un produit
	 *
	 * @param id
	 */
	@Override
	public void deleteProduit(final Long id) {
		if (id == null) {
			log.error("ce produit n'existe pas!");
			return;
		}
		produitRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des produits
	 *
	 * @return List<ProduitDto>
	 */
	@Override
	public List<ProduitDto> fetchProduits() {
		return produitRepository.findAll().stream().map(produit ->
				mapper.map(produit, ProduitDto.class)).collect(Collectors.toList());
	}

	/**
	 * .
	 *
	 * @param dto
	 * @return SuccursaleDto
	 */
	@Override
	public SuccursaleDto createAndUpdateSucc(final SuccursaleDto dto) {
		Succursale entity = mapper.map(dto, Succursale.class);
		entity = succursaleRepository.save(entity);
		return mapper.map(entity, SuccursaleDto.class);
	}

	/**
	 * .
	 * supprimer un succ
	 *
	 * @param id
	 */
	@Override
	public void deleteSuccursale(final Long id) {
		if (id == null) {
			log.error("N'existe pas !");
			return;
		}
		succursaleRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des Succursales
	 *
	 * @return List<SuccursaleDto>
	 */
	@Override
	public List<SuccursaleDto> fetchSuccursales() {
		return succursaleRepository.findAll().stream().map(succursale ->
				mapper.map(succursale, SuccursaleDto.class)).collect(Collectors.toList());
	}

	/**
	 * .
	 *
	 * @param dto
	 * @return TypeClientDto
	 */
	@Override
	public TypeClientDto createAndUpdateTypeClt(final TypeClientDto dto) {
		TypeClient entity = mapper.map(dto, TypeClient.class);
		entity = typeClientRepository.save(entity);
		return mapper.map(entity, TypeClientDto.class);
	}

	/**
	 * .
	 * supprimer un succ
	 *
	 * @param id
	 */
	@Override
	public void deleteTypeClient(final Long id) {
		if (id == null) {
			log.error("type client n'existe pas !");
			return;
		}
		typeClientRepository.deleteById(id);
	}

	/**
	 * .
	 * liste des TypeClients
	 *
	 * @return List<TypeClientDto>
	 */
	@Override
	public List<TypeClientDto> fetchTypeClient() {
		return typeClientRepository.findAll().stream().map(typeClient ->
				mapper.map(typeClient, TypeClientDto.class)).collect(Collectors.toList());
	}


}
