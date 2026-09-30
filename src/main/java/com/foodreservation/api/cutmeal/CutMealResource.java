package com.foodreservation.api.cutmeal;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/cutmeal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CutMealResource {

    // TODO: GET all -> List<CutMealDto>
    // TODO: GET /{id} -> CutMealDto
    // TODO: POST -> create من CutMealDto
    // TODO: PUT /{id} -> update من CutMealDto
    // TODO: DELETE /{id}
}
