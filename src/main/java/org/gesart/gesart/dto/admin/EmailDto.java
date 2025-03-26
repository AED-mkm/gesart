package org.gesart.gesart.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author : <a href="mohamskab@outlook.fr">KABORE Mohamadi</a>
 * @version : 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EmailDto {
    private String to;
    private String from;
    private String subject;
    private String template;
    private Map<String, Object> properties;
    private String boutique;
    private String dateFin;
}
