package com.foodreservation.mapper.order;

import com.foodreservation.model.day.DaysOfWeeks;
import com.foodreservation.dto.order.OrderDto;
import com.foodreservation.model.order.Reservation;

public class OrderMapper {

    public static OrderDto toDto(Reservation entity) {
       OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setReservationTime(entity.getReservationTime());
        dto.setEndTime(entity.getEndTime());
        dto.setCut(entity.isCut());
        dto.setCutTime(entity.getCutTime());
        dto.setDaysOfWeeksId(entity.getDaysOfWeeks().getId());
        dto.setMealType(entity.getDaysOfWeeks().getMealType()); // محسوبة من العلاقة
        return dto;
    }

    public static Reservation toEntity(OrderDto dto, DaysOfWeeks daysOfWeeks) {
       Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setReservationTime(dto.getReservationTime());
        entity.setEndTime(dto.getEndTime());
        entity.setCut(dto.isCut());
        entity.setCutTime(dto.getCutTime());
        entity.setDaysOfWeeks(daysOfWeeks);
        return entity;
    }
}
