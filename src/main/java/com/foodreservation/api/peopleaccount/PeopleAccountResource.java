package com.foodreservation.api.peopleaccount;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/peopleaccount")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleAccountResource {

    // TODO: GET all -> List<PeopleAccountDto>
    // TODO: GET /{id} -> PeopleAccountDto
    // TODO: POST -> create من PeopleAccountDto
    // TODO: PUT /{id} -> update من PeopleAccountDto
    // TODO: DELETE /{id}
}
