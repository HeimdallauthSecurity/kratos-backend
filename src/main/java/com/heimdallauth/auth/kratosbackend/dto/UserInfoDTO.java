package com.heimdallauth.auth.kratosbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDTO {
    @NotNull(message = "ID cannot be null")
    private String id;

    @NotBlank(message = "Username cannot be null or empty")
    private String username;

    @NotBlank(message = "Email cannot be null or empty")
    private String email;

    @NotBlank(message = "First name cannot be null or empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be null or empty")
    private String lastName;

    @NotNull(message = "Roles cannot be null")
    private List<String> roles;
}
