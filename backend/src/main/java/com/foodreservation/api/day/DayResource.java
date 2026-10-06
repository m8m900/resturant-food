package com.foodreservation.api.day;

import com.foodreservation.dto.day.DayDto;
import com.foodreservation.mapper.day.DayMapper;
import com.foodreservation.model.day.DaysOfWeeks;
import com.foodreservation.model.meal.MealOfCard;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import com.foodreservation.service.day.DaysOfWeeksService;
import com.foodreservation.service.meal.MealCardFacade;
import com.foodreservation.service.restaurantcard.RestaurantCardFacade;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/days")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DayResource {

@Inject 
private DaysOfWeeksService daysOfWeeksService;

@Inject 
private MealCardFacade mealCardFacade;

@Inject 
private RestaurantCardFacade restaurantCardFacade;

@GET 
public List<DayDto> getAll(){
    return daysOfWeeksService.findAll().stream().map(DayMapper::toDto).collect(Collectors.toList());
}
@GET 
@Path("/{id}")
public Response getById(@PathParam("id") Long id) {
    DaysOfWeeks daysOfWeeks = daysOfWeeksService.findById(id);
    if (daysOfWeeks == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(DayMapper.toDto(daysOfWeeks)).build();
}
@POST 
public Response create(@Valid DayDto dto){
    MealOfCard mealOfCard = mealCardFacade.findById(dto.getMealOfCardId());
    RestaurantOfCard restaurantOfCard = restaurantCardFacade.findById(dto.getRestaurantOfCardId());
    if (mealOfCard == null || restaurantOfCard == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("المطعم غير مسجل او الوجبة غير متوفرة").build();
    }
    DaysOfWeeks daysOfWeeks = DayMapper.toEntity(dto, mealOfCard, restaurantOfCard);
    daysOfWeeksService.create(daysOfWeeks);
    return Response.status(Response.Status.CREATED).entity(DayMapper.toDto(daysOfWeeks)).build();
}

@PUT
@Path("/{id}")
public Response update(@PathParam("id") Long id, @Valid DayDto dto) {
    DaysOfWeeks daysOfWeeks = daysOfWeeksService.findById(id);
    if (daysOfWeeks == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    MealOfCard mealOfCard = mealCardFacade.findById(dto.getMealOfCardId());
    RestaurantOfCard restaurantOfCard = restaurantCardFacade.findById(dto.getRestaurantOfCardId());
    if (mealOfCard == null || restaurantOfCard == null) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("المطعم غير مسجل او الوجبة غير متوفرة")
                .build();
    }
    daysOfWeeks.setDate(dto.getDate());
    daysOfWeeks.setMealType(dto.getMealType());
    daysOfWeeks.setMealOfCard(mealOfCard);
    daysOfWeeks.setRestaurantOfCard(restaurantOfCard);
    daysOfWeeksService.create(daysOfWeeks);
    return Response.ok(DayMapper.toDto(daysOfWeeks)).build();
}
@DELETE
@Path("/{id}")
public Response delete(@PathParam("id") Long id) {
    DaysOfWeeks daysOfWeeks = daysOfWeeksService.findById(id);
    if (daysOfWeeks == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    daysOfWeeksService.remove(daysOfWeeks);
    return Response.noContent().build();
}

}
