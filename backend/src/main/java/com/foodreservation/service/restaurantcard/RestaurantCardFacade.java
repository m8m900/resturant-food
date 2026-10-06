package com.foodreservation.service.restaurantcard;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import com.foodreservation.service.AbstractFacade;

@Stateless
public class RestaurantCardFacade extends AbstractFacade<RestaurantOfCard> {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    public RestaurantCardFacade() {
        super(RestaurantOfCard.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
