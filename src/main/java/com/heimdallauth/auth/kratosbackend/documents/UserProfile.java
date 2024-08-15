package com.heimdallauth.auth.kratosbackend.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfile {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String role;
    private boolean active;
    private String firstName;
    private String lastName;
    private List<ContactInformation> phoneNumbers;
    private Instant creationTimestamp;
    private Instant updateTimestamp;
}
