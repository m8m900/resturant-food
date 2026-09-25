package order.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import order.entity.Reservation;
import java.util.List;

@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public ReservationFacade() {
        super(Reservation.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    public List<Reservation> getAllReservations() {
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }
    
}