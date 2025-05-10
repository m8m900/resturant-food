package restaurantCard.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import restaurantCard.entity.RestaurantOfCard;

@Stateless
public class RestaurantCardFacade extends AbstractFacade<RestaurantOfCard> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;
    public RestaurantCardFacade() {
        super(RestaurantOfCard.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
