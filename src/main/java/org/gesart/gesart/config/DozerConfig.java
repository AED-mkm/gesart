package org.gesart.gesart.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;
import org.gesart.gesart.domain.traitement.BonDeCmdeFour;
import org.gesart.gesart.domain.traitement.Entre;
import org.gesart.gesart.domain.traitement.ProdBonCmdeFour;
import org.gesart.gesart.domain.parametrage.Succursale;
import org.gesart.gesart.domain.parametrage.Banque;
import org.gesart.gesart.domain.parametrage.Client;
import org.gesart.gesart.domain.parametrage.Fournisseur;
import org.gesart.gesart.domain.parametrage.Produit;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.domain.parametrage.TypeClient;
import org.gesart.gesart.domain.admin.Profil;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.dto.admin.ProfilDto;
import org.gesart.gesart.dto.admin.UserDto;
import org.gesart.gesart.dto.parametrage.SuccursaleDto;
import org.gesart.gesart.dto.parametrage.BanqueDto;
import org.gesart.gesart.dto.parametrage.ClientDto;
import org.gesart.gesart.dto.parametrage.FournisseurDto;
import org.gesart.gesart.dto.parametrage.ProduitDto;
import org.gesart.gesart.dto.parametrage.MagasinDto;
import org.gesart.gesart.dto.parametrage.TypeClientDto;
import org.gesart.gesart.dto.traitement.BonDeCmdeFourDto;
import org.gesart.gesart.dto.traitement.EntreDto;
import org.gesart.gesart.dto.traitement.ProdBonCmdeFourDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class DozerConfig {
    private final BeanMappingBuilder builder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(UserDto.class, User.class, TypeMappingOptions.mapNull(false))
                    .fields("password", "password", FieldsMappingOptions.oneWay())
                    .fields("profilLibelle", "profil.libelle")
                    .fields("profilId", "profil.id");
            mapping(ProfilDto.class, Profil.class, TypeMappingOptions.mapNull(false));
            mapping(SuccursaleDto.class, Succursale.class, TypeMappingOptions.mapNull(false))
                     .fields("banqLibelle", "banque.libellebanque")
                    .fields("banqueId", "banque.id");
            mapping(BanqueDto.class, Banque.class, TypeMappingOptions.mapNull(false));
            mapping(ClientDto.class, Client.class, TypeMappingOptions.mapNull(false))
                    .fields("typeClientId","typeclient.id")
                    .fields("magasinId", "magasin.id");
            mapping(FournisseurDto.class, Fournisseur.class, TypeMappingOptions.mapNull(false))
                    .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");
            mapping(ProduitDto.class, Produit.class, TypeMappingOptions.mapNull(false))
                    .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");
            mapping(MagasinDto.class, Magasin.class, TypeMappingOptions.mapNull(false));
            mapping(TypeClientDto.class, TypeClient.class, TypeMappingOptions.mapNull(false));
            mapping(EntreDto.class, Entre.class, TypeMappingOptions.mapNull(false))
                    .fields("FournisseurId", "fournisseur.id")
                    .fields("magasinId", "magasin.id")
                    .fields("responsableMag", "magasin.responsableMag");
            mapping(BonDeCmdeFourDto.class, BonDeCmdeFour.class, TypeMappingOptions.mapNull(false))
                    .fields("nomFour", "fournisseur.nomFour")
                    .fields("FournisseurId", "fournisseur.id")
                    .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id")
                    .fields("prodBonCmdeFourDto", "prodBonCmdeFours");
            mapping(ProdBonCmdeFourDto.class, ProdBonCmdeFour.class, TypeMappingOptions.mapNull(false))
                    .fields("produitId", "produit.id");

        }

    };

    /**
     * builds the dozer mapper.
     *
     * @return Mapper
     */
    @Bean
    public Mapper buildDozerMapper() {
        return DozerBeanMapperBuilder.create()
                .withMappingBuilder(builder)
                .build();
    }
}
