package com.heimdallauth.auth.kratosbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserProfileDTO {
    @NotBlank(message = "Username cannot be null or empty")
    private String username;
    @NotBlank(message = "Email cannot be null or empty")
    private String email;
    @NotBlank(message = "First name cannot be null or empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be null or empty")
    private String lastName;

}
