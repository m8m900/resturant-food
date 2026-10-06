package com.foodreservation.api.meal;

import com.foodreservation.dto.meal.MealDto;
import com.foodreservation.mapper.meal.MealMapper;
import com.foodreservation.model.meal.MealOfCard;
import com.foodreservation.service.meal.MealCardFacade;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/meals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MealResource {
    @Inject 
    private MealCardFacade mealCardFacade;

    @GET
    public List<MealDto> getAll() {
        return mealCardFacade.findAll().stream().map(MealMapper::toDto).collect(Collectors.toList());
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        MealOfCard entity = mealCardFacade.findById(id);
        if (entity == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(MealMapper.toDto(entity)).build();
    }
    @POST
    public Response create(@Valid MealDto dto) {
        MealOfCard entity = MealMapper.toEntity(dto);
        mealCardFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(MealMapper.toDto(entity)).build();
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid MealDto dto) {
        MealOfCard entity = mealCardFacade.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entity.setIngredients(dto.getIngredients());
        entity.setPrice(dto.getPrice());
        entity.setDetails(dto.getDetails());
        entity.setMealType(dto.getMealType());
        mealCardFacade.create(entity);
        return Response.ok(MealMapper.toDto(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        MealOfCard entity = mealCardFacade.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mealCardFacade.remove(entity);
        return Response.noContent().build();
    }

}
