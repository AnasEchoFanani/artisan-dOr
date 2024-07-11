package com.app.artisandor.services.interfaces;

import com.app.artisandor.dto.AuthenticationRequest;
import com.app.artisandor.dto.AuthenticationResponse;
import com.app.artisandor.dto.RegisterRequest;

public interface AuthenticationService {

    /**
     * Registers a new user.
     *
     * @param request the registration request data
     * @return an authentication response containing the result of the registration
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Authenticates a user.
     *
     * @param request the authentication request data
     * @return an authentication response containing the result of the authentication
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);

    /**
     * Retrieves a default user (for testing or initialization purposes).
     *
     * @return an authentication response containing the default user data
     */
    AuthenticationResponse defaultUser();
}
