package day.service;

import AbstractFacade.AbstractFacade;
import day.entity.DaysOfWeeks;
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


