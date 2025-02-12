package org.gesart.gesart.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : <a href="siguizana08@gmail.com">BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TokenDto {
    private String token;
}
