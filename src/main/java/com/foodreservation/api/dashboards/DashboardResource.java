package com.foodreservation.api.dashboards;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DashboardResource {

    // TODO: GET all -> List<DashboardDto>
    // TODO: GET /{id} -> DashboardDto
    // TODO: POST -> create من DashboardDto
    // TODO: PUT /{id} -> update من DashboardDto
    // TODO: DELETE /{id}
}
