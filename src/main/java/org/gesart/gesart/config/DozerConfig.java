package org.gesart.gesart.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;
import org.gesart.gesart.domain.Succursale;
import org.gesart.gesart.domain.Banque;
import org.gesart.gesart.domain.Client;
import org.gesart.gesart.domain.Fournisseur;
import org.gesart.gesart.domain.Produit;
import org.gesart.gesart.domain.Magasin;
import org.gesart.gesart.domain.TypeClient;
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
            mapping(SuccursaleDto.class, Succursale.class, TypeMappingOptions.mapNull(false));
                  /*  .fields("banqLibelle", "banque.libellebanque")
                    .fields("banqueId", "banque.id");*/
            mapping(BanqueDto.class, Banque.class, TypeMappingOptions.mapNull(false));
                 /*   .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");*/
            mapping(ClientDto.class, Client.class, TypeMappingOptions.mapNull(false));
                /*.fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");*/
            mapping(FournisseurDto.class, Fournisseur.class, TypeMappingOptions.mapNull(false));
                   /* .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");*/
            mapping(ProduitDto.class, Produit.class, TypeMappingOptions.mapNull(false))
                    .fields("libelleMag", "magasin.nomMagasin")
                    .fields("magasinId", "magasin.id");
            mapping(MagasinDto.class, Magasin.class, TypeMappingOptions.mapNull(false));
            mapping(TypeClientDto.class, TypeClient.class, TypeMappingOptions.mapNull(false));
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
