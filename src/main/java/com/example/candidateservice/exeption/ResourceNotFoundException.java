package com.example.candidateservice.exeption;


public class ResourceNotFoundException extends RuntimeException {

    public static final String NOT_FOUND_WITH_ID_MESSAGE = "%s not found with id: %d";

    public static final String NOT_FOUND_MESSAGE = "%s not found";

    public ResourceNotFoundException(final String domainName, final long id) {
        super(String.format(NOT_FOUND_WITH_ID_MESSAGE, domainName, id));
    }

    public ResourceNotFoundException(final String domainName) {
        super(String.format(NOT_FOUND_MESSAGE, domainName));
    }

}
