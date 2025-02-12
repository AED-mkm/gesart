package org.gesart.gesart.dto.admin;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.gesart.gesart.domain.enums.TypeStatut;


import java.io.Serializable;

@Data
public abstract class AbstractAuditEntityDto implements Serializable {
    @Enumerated(EnumType.STRING)
    private TypeStatut statut = TypeStatut.ACTIF;
}
