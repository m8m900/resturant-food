package com.foodreservation.api.restaurantcard;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import java.util.List;
import java.util.stream.Collectors;
import com.foodreservation.dto.restaurantcard.RestaurantCardDto;
import com.foodreservation.mapper.restaurantcard.RestaurantCardMapper;
import com.foodreservation.service.restaurantcard.RestaurantCardFacade;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantCardResource {

    @Inject
    private RestaurantCardFacade restaurantCardFacade;
    @GET  // لجلب كل المطاعم او لسته من المطاعم 
    public List<RestaurantCardDto>getAll(){
        return restaurantCardFacade.findAll().stream().map(RestaurantCardMapper::toDto).collect(Collectors.toList());
    }
    @GET
    @Path("/{id}") 
    public Response getById(@PathParam("id") Long id) {
    RestaurantOfCard entity = restaurantCardFacade.findById(id);
    if (entity == null) {
    return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(RestaurantCardMapper.toDto(entity)).build();
    }
    @POST
    public Response create(@Valid RestaurantCardDto dto) {
    RestaurantOfCard entity = RestaurantCardMapper.toEntity(dto);//تحويل لشكل البيانات الى dto 
    restaurantCardFacade.create(entity);//حتى نحفظ البيانات 
    return Response.status(Response.Status.CREATED) .entity(RestaurantCardMapper.toDto(entity)).build();//الرد النهائي 
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid RestaurantCardDto dto) {
    RestaurantOfCard entity = restaurantCardFacade.findById(id);
    if (entity == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    entity.setName(dto.getName());
    entity.setSite(dto.getSite());
    restaurantCardFacade.create(entity);
    return Response.ok(RestaurantCardMapper.toDto(entity)).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
    RestaurantOfCard entity = restaurantCardFacade.findById(id);
    if (entity == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    restaurantCardFacade.remove(entity);
    return Response.noContent().build();
    }
}
