package org.gesart.gesart.dto.admin;

import lombok.Data;
import lombok.NonNull;

@Data
public class PasswordChangedDto {
    @NonNull
    private String currentPassword;
    @NonNull
    private String password;
    @NonNull
    private byte[] photo;
}
