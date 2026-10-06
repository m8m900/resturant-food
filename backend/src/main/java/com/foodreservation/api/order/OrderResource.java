package com.foodreservation.api.order;

import com.foodreservation.dto.order.OrderDto;
import com.foodreservation.mapper.order.OrderMapper;
import com.foodreservation.model.day.DaysOfWeeks;
import com.foodreservation.model.order.Reservation;
import com.foodreservation.service.day.DaysOfWeeksService;
import com.foodreservation.service.order.ReservationFacade;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

     @Inject
    private ReservationFacade reservationFacade;

    @Inject
    private DaysOfWeeksService daysOfWeeksService;

    @GET
    public List<OrderDto> getAll() {
        return reservationFacade.findAll().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Reservation reservation = reservationFacade.findById(id);
        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(OrderMapper.toDto(reservation)).build();
    }

    @POST
    public Response create(@Valid OrderDto dto) {
        DaysOfWeeks daysOfWeeks = daysOfWeeksService.findById(dto.getDaysOfWeeksId());
        if (daysOfWeeks == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("اليوم/الوجبة المحددة غير موجودة")
                    .build();
        }
        Reservation reservation = OrderMapper.toEntity(dto, daysOfWeeks);
        reservationFacade.create(reservation);
        return Response.status(Response.Status.CREATED)
                .entity(OrderMapper.toDto(reservation))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid OrderDto dto) {
        Reservation reservation = reservationFacade.findById(id);
        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        DaysOfWeeks daysOfWeeks = daysOfWeeksService.findById(dto.getDaysOfWeeksId());
        if (daysOfWeeks == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("اليوم/الوجبة المحددة غير موجودة")
                    .build();
        }
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setCut(dto.isCut());
        reservation.setCutTime(dto.getCutTime());
        reservation.setDaysOfWeeks(daysOfWeeks);
        reservationFacade.create(reservation);
        return Response.ok(OrderMapper.toDto(reservation)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Reservation reservation = reservationFacade.findById(id);
        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        reservationFacade.remove(reservation);
        return Response.noContent().build();
    }
}
