package com.foodreservation.api.meal;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/meal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MealResource {

    // TODO: GET all -> List<MealDto>
    // TODO: GET /{id} -> MealDto
    // TODO: POST -> create من MealDto
    // TODO: PUT /{id} -> update من MealDto
    // TODO: DELETE /{id}
}
