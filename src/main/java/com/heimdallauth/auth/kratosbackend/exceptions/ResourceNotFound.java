package com.heimdallauth.auth.kratosbackend.exceptions;

public class ResourceNotFound extends RuntimeException {
    public final String resourceType;
    public final String resourceIdentifier;
    public final String incidentId;
    public ResourceNotFound(String message, String resourceType, String resourceIdentifier, String incidentId) {
        super(message);
        this.resourceType = resourceType;
        this.resourceIdentifier = resourceIdentifier;
        this.incidentId = incidentId;
    }
}
