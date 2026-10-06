package com.foodreservation.service.day;

import com.foodreservation.model.day.DaysOfWeeks;
import com.foodreservation.service.AbstractFacade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class DaysOfWeeksService extends AbstractFacade<DaysOfWeeks> {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    public DaysOfWeeksService() {
        super(DaysOfWeeks.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }}


