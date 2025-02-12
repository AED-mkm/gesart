package org.gesart.gesart.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class CommentaireDto extends AbstractAuditEntityDto {
    private Long id;
    private String nom;
    private String email;
    private String message;
    private Instant datePost;
    private Long actualiteId;
    private String telephone;
}
