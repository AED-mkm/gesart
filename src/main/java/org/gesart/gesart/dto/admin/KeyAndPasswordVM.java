package org.gesart.gesart.dto.admin;

import lombok.Data;

@Data
public class KeyAndPasswordVM {
    private String key;
    private String oldPassword;
    private String newPassword;
}
