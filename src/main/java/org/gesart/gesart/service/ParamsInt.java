package org.gesart.gesart.service;

import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.domain.parametrage.Taxe;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.domain.parametrage.TypeReglement;
import org.gesart.gesart.dto.parametrage.TaxeDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.TypeReglDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ParamsInt {
	/**
	 * .
	 *
	 * @param dto Creation des clients
	 * @return ClientDto
	 */
	ClientDto createAndUpdateClient(ClientDto dto);

	/**
	 * .
	 * supprimer un client
	 *
	 * @param id
	 */
	void deleteClient(Long id);

	/**
	 * .
	 * liste des clients
	 *
	 * @return List<ClientDto>
	 */
	List<ClientDto> fetchClients();

	Optional<ClientDto> findClientById(Long id);

	/**.
	 * Pagination des listes
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return ClientDto
	 */

	Page<Client> findPage(int pageNo, int pageSize, String sortBy);

	/**.
	 * creation et mise à jour des banque
	 *
	 * @param dto
	 * @return BanqueDto
	 */
	BanqueDto createAndUpdateBanque(BanqueDto dto);

	/**
	 * .
	 * supprimer un client
	 *
	 * @param id
	 */
	void deleteBanque(Long id);

	/**
	 * .
	 * liste des clients
	 *
	 * @return List<BanqueDto>
	 */
	List<BanqueDto> fetchBanques();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<BanqueDto>
	 */

	Page<Banque> findPageBanque(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 * creation et mise à jour des fournisseurs
	 *
	 * @param dto
	 * @return FournisseurDto
	 */
	FournisseurDto createAndUpdateFour(FournisseurDto dto);

	/**
	 * .
	 * supprimer un fournisseur
	 *
	 * @param id
	 */
	void deleteFournisseur(Long id);

	/**
	 * .
	 * liste des fournisseurs
	 *
	 * @return List<FournisseurDto>
	 */
	List<FournisseurDto> fetchFournisseurs();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return FournisseurDto
	 */

	Page<Fournisseur> findPageFourn(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 *
	 * @param dto
	 * @return MagasinDto
	 */
	MagasinDto createAndUpdateMg(MagasinDto dto);

	/**.
	 * @param magasinId
	 * @return Magasin
	 */
	Optional<Magasin> findById(Long magasinId);

	/**.
	 * @param id
	 */

	void deleteMagasin(Long id);

	/**
	 * .
	 * liste des magasins
	 *
	 * @return List<MagasinDto>
	 */
	List<MagasinDto> fetchMagasins();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<MagasinDto>
	 */

	Page<Magasin> findPageMagasin(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 * creation de produits
	 *
	 * @param dto
	 * @return ProduitDto
	 */
	ProduitDto createAndUpdateProd(ProduitDto dto);

	/**
	 * .
	 * supprimer un produit
	 *
	 * @param id
	 */
	void deleteProduit(Long id);


	Produit updatePrixProd(Long id, BigDecimal nouveauPrix);


	Produit updateCoutProd(Long id, BigDecimal nouveauCoutAchat);

	/**
	 * .
	 * liste des produits
	 *
	 * @return List<ProduitDto>
	 */
	List<ProduitDto> fetchProduits();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProduitDto>
	 */

	Page<Produit> findPageProduit(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 *
	 * @param dto
	 * @return SuccursaleDto
	 */
	SuccursaleDto createAndUpdateSucc(SuccursaleDto dto);

	/**
	 * .
	 * supprimer un succ
	 *
	 * @param id
	 */
	void deleteSuccursale(Long id);

	/**
	 * .
	 * liste des Succursales
	 *
	 * @return List<SuccursaleDto>
	 */
	List<SuccursaleDto> fetchSuccursales();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<SuccursaleDto>
	 */

	Page<Succursale> findPageSuccursale(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 *
	 * @param dto
	 * @return TypeClientDto
	 */
	TypeClientDto createAndUpdateTypeClt(TypeClientDto dto);

	/**
	 * .
	 * supprimer un succ
	 *
	 * @param id
	 */
	void deleteTypeClient(Long id);

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<TypeClientDto>
	 */

	Page<TypeClient> findPageTypeClient(int pageNo, int pageSize, String sortBy);

	/**
	 * .
	 * liste des TypeClients
	 *
	 * @return List<TypeClientDto>
	 */
	List<TypeClientDto> fetchTypeClient();

	/**.
	 * @param dto
	 * @return TaxeDto
	 */
	TaxeDto createAndUpdateTaxes(TaxeDto dto);

	/**
	 * .
	 * supprimer une taxe
	 *
	 * @param id
	 */
	void deleteTaxe(Long id);

	/**.
	 * liste des taxes
	 *
	 * @return List<TaxeDto>
	 */
	List<TaxeDto> fetchTaxes();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<TaxeDto>
	 */

	Page<Taxe> findPageTaxe(int pageNo, int pageSize, String sortBy);

	/**.
	 * @param dto
	 * @return TypeReglDto
	 */

	TypeReglDto createAndUpdateTypeRegl(TypeReglDto dto);

	/**.
	 * @param id
	 */
	void deleteTyperegl(Long id);

	/**.
	 *
	 * @return List<TypeReglDto>
	 */
	List<TypeReglDto> fetchTypeRegl();

	/**.
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<TypeReglDto>
	 */

	Page<TypeReglement> findPageTypeRegl(int pageNo, int pageSize, String sortBy);
}
