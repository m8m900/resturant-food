package com.foodreservation.api.day;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/day")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DayResource {

    // TODO: GET all -> List<DayDto>
    // TODO: GET /{id} -> DayDto
    // TODO: POST -> create من DayDto
    // TODO: PUT /{id} -> update من DayDto
    // TODO: DELETE /{id}
}
