package org.gesart.gesart.organisation;

import org.gesart.gesart.domain.Client;
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

import java.util.List;

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
	void deleteFournisseure(Long id);

	/**
	 * .
	 * liste des fournisseurs
	 *
	 * @return List<FournisseurDto>
	 */
	List<FournisseurDto> fetchFournisseurs();

	/**
	 * .
	 *
	 * @param dto
	 * @return MagasinDto
	 */
	MagasinDto createAndUpdateMg(MagasinDto dto);

	/**
	 * .
	 * supprimer un fournisseur
	 *
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

	/**
	 * .
	 * liste des produits
	 *
	 * @return List<ProduitDto>
	 */
	List<ProduitDto> fetchProduits();

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
	 * @param dto
	 * @return TypeReglDto
	 */

	TypeReglDto createAndUpdateTypeRegl(TypeReglDto dto);

	/**.
	 * @param id
	 */
	void deletetyperegl(Long id);

	/**.
	 *
	 * @return List<TypeReglDto>
	 */
	List<TypeReglDto> fetchTypeRegl();

}
