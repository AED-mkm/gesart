package org.gesart.gesart.serviceImpl.Parametrage;


import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.historique.HistoriqueCout;
import org.gesart.gesart.domain.historique.HistoriquePrix;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.TypeReglement;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.TaxeDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.dto.parametrage.TypeReglDto;
import org.gesart.gesart.repository.historique.HistoriqueCoutRepository;
import org.gesart.gesart.repository.historique.HistoriquePrixRepository;
import org.gesart.gesart.service.ParamsInt;
import org.gesart.gesart.repository.parametrage.SuccursaleRepository;
import org.gesart.gesart.repository.parametrage.ClientRepository;
import org.gesart.gesart.repository.parametrage.FournisseurRepository;
import org.gesart.gesart.repository.parametrage.TaxeRepository;
import org.gesart.gesart.repository.parametrage.TypeClientRepository;
import org.gesart.gesart.repository.parametrage.BanqueRepository;
import org.gesart.gesart.repository.parametrage.MagasinRepository;
import org.gesart.gesart.repository.parametrage.ProduitRepository;
import org.gesart.gesart.repository.parametrage.TypeReglRepository;
import org.gesart.gesart.repository.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class ParametreService implements ParamsInt {

	@Autowired
	private final Mapper mapper;
	private final ClientRepository clientRepository;
	private final BanqueRepository banqueRepository;
	private final FournisseurRepository fournisseurRepository;
	private final MagasinRepository magasinRepository;
	private final ProduitRepository produitRepository;
	private final SuccursaleRepository succursaleRepository;
	private final TypeClientRepository typeClientRepository;
	private final TaxeRepository taxeRepository;
	private final TypeReglRepository typeReglRepository;
	private final UserRepository userRepository;
	private final HistoriqueCoutRepository historiqueCoutRepository;
	private HistoriquePrixRepository historiquePrixRepository;

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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@Override
	public Page<Banque> findPageBanque(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return banqueRepository.findAll(pageable);
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
	public void deleteFournisseur(final Long id) {
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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<Fournisseur> findPageFourn(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return fournisseurRepository.findAll(pageable);
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
	 * @param magasinId
	 *
	 * @return
	 */
	@Override
	public Optional<Magasin> findById(Long magasinId) {
		Optional<Magasin> magasin = magasinRepository.findById(magasinId);
		return magasin;
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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<Magasin> findPageMagasin(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return magasinRepository.findAll(pageable);
	}

	/**.
	 * @param userId
	 * @return
	 */


	public Long getMagasinId(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			return user.getMagasin().getId();
		}
		return null;
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
	 * @param id
	 * @param nouveauPrix
	 * @return produit
	 */
	@Override
	public Produit updatePrixProd(Long id, BigDecimal nouveauPrix) {
		Produit produit = produitRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));
		//enregistrer le peix actuel
		HistoriquePrix historique = new HistoriquePrix();
		historique.setProduit(produit);
		historique.setAncienPrix(produit.getPrixActuel());
		historique.setNouveauPrix(nouveauPrix);
		historiquePrixRepository.save(historique);
		//mettre à jour le prix
		produit.setAncienPrix(produit.getPrixActuel());
		produit.setPrixActuel(nouveauPrix);
		return produitRepository.save(produit);
	}

	/**
	 * @param id
	 * @param nouveauPrix
	 *
	 * @return
	 */
	@Override
	public Produit updateCoutProd(Long id, BigDecimal nouveauCoutAchat) {
		Produit produit = produitRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));
		// enregistrer l'ancien coût d'achat
		HistoriqueCout historique = new HistoriqueCout();
		historique.setProduit(produit);
		historique.setAncienCoutAchat(produit.getCoutAchat());
		historique.setNouveauCoutAchat(nouveauCoutAchat);
		historiqueCoutRepository.save(historique);

		// mettre à jour le produit
		produit.setAncienCoutAchat(produit.getCoutAchat());
		produit.setCoutAchat(nouveauCoutAchat);
		return produitRepository.save(produit);
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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<Produit> findPageProduit(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return produitRepository.findAll(pageable);
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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<Succursale> findPageSuccursale(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return succursaleRepository.findAll(pageable);
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

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<TypeClient> findPageTypeClient(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return typeClientRepository.findAll(pageable);
	}

	/**.
	 * @param dto
	 *
	 * @return TaxeDto
	 */
	@Override
	public TaxeDto createAndUpdateTaxes(final TaxeDto dto) {
		Taxe entity = mapper.map(dto, Taxe.class);
		entity = taxeRepository.save(entity);
		return mapper.map(entity, TaxeDto.class);
	}

	/**.
	 * supprimer une taxe
	 *
	 * @param id
	 */
	@Override
	public void deleteTaxe(final Long id) {
		if (id == null) {
			log.error("taxe n'existe pas !");
			return;
		}
		taxeRepository.deleteById(id);

	}

	/**.
	 * liste des taxes
	 *
	 * @return List<TaxeDto>
	 */
	@Override
	public List<TaxeDto> fetchTaxes() {
		return taxeRepository.findAll().stream().map(taxe ->
				mapper.map(taxe, TaxeDto.class)).collect(Collectors.toList());
	}

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<Taxe> findPageTaxe(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return taxeRepository.findAll(pageable);
	}


	/**.
	 *
	 * @param dto
	 * @return TypeReglDto
	 */
	public TypeReglDto createAndUpdateTypeRegl(final TypeReglDto dto) {
		TypeReglement entity = mapper.map(dto, TypeReglement.class);
		entity = typeReglRepository.save(entity);
		return mapper.map(entity, TypeReglDto.class);
	}

	/**
	 * .
	 *
	 * @param id
	 */
	@Override
	public void deleteTyperegl(final Long id) {
		if (id == null) {
			log.error("taxe n'existe pas !");
			return;
		}
		typeReglRepository.deleteById(id);

	}

	/**
	 * .
	 *
	 * @return List<TypeReglDto>
	 */
	@Override
	public List<TypeReglDto> fetchTypeRegl() {
		return typeReglRepository.findAll().stream().map(taxe ->
				mapper.map(taxe, TypeReglDto.class)).collect(Collectors.toList());
	}

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */

	@Override
	public Page<TypeReglement> findPageTypeRegl(final int pageNo, final int pageSize, final String sortBy) {
		Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return typeReglRepository.findAll(pageable);
	}

}
