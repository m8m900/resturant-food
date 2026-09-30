package com.foodreservation.api.login;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    // TODO: GET all -> List<LoginDto>
    // TODO: GET /{id} -> LoginDto
    // TODO: POST -> create من LoginDto
    // TODO: PUT /{id} -> update من LoginDto
    // TODO: DELETE /{id}
}
