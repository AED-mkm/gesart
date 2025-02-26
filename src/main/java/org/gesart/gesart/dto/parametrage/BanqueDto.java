package org.gesart.gesart.dto.parametrage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

/**
 * @author Moctar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class BanqueDto {
    private Long id;
    private String codeBanque;
    private String libellebanque;
    private String contact;
    /*@JsonIgnore
    @OneToMany(mappedBy = "banque")
    private List<Magasin> magasins;*/
    //private List<SuccursaleDto> succursales;
    //private MagasinDto magasin = new MagasinDto();
    private Long magasinId;
    private String libelleMag;
}