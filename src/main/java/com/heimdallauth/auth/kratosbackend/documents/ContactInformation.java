package com.heimdallauth.auth.kratosbackend.documents;

import com.heimdallauth.auth.kratosbackend.models.PhoneNumberModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ContactInformation {
    private PhoneNumberModel phoneNumberModel;
}
