package order.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import order.entity.ReservationSettings;

import java.util.List;

@Stateless
public class ReservationSettingsFacade extends AbstractFacade<ReservationSettings> {

    @PersistenceContext
    private EntityManager em;
    public ReservationSettingsFacade() {
        super(ReservationSettings.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }


    public ReservationSettings getSettings() {
        List<ReservationSettings> settings = em.createQuery("SELECT r FROM ReservationSettings r", ReservationSettings.class).getResultList();
        return settings.isEmpty() ? null : settings.get(0);
    }

    public void saveSettings(ReservationSettings settings) {
        if (settings.getId() == null) {
            em.persist(settings);
        } else {
            em.merge(settings);
        }
    }
}
