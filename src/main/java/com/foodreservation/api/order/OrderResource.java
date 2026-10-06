package com.foodreservation.api.order;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    // TODO: GET all -> List<OrderDto>
    // TODO: GET /{id} -> OrderDto
    // TODO: POST -> create من OrderDto
    // TODO: PUT /{id} -> update من OrderDto
    // TODO: DELETE /{id}
}
