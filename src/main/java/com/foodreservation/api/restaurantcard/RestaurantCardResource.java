package com.foodreservation.api.restaurantcard;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import com.foodreservation.service.restaurantcard.RestaurantCardFacade;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantCardResource {

    @Inject
    private RestaurantCardFacade facade;

    // TODO: GET all -> List<RestaurantCardDto>
    // TODO: GET /{id} -> RestaurantCardDto
    // TODO: POST -> create من RestaurantCardDto
    // TODO: PUT /{id} -> update من RestaurantCardDto
    // TODO: DELETE /{id}
}
