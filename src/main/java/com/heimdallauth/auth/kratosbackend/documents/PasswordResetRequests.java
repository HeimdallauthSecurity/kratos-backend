package com.heimdallauth.auth.kratosbackend.documents;

import com.heimdallauth.auth.kratosbackend.constants.PasswordResetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetRequests {
    @Id
    private String requestId;
    private Instant requestCreationTimestamp;
    private PasswordResetStatus resetStatus;
    @Indexed(unique = false)
    private String userProfileId;
    private Instant requestExpirationTimestamp;
}
