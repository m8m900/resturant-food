package com.foodreservation.service.meal;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.foodreservation.model.meal.MealOfCard;
import com.foodreservation.service.AbstractFacade;
import com.foodreservation.model.common.MealType;

import java.util.List;

@Stateless
    public class MealCardFacade extends AbstractFacade<MealOfCard> {
    @PersistenceContext(unitName = "appPU")
    private  EntityManager em;

    public MealCardFacade() {
        super(MealOfCard.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    public List<MealOfCard> findMealsByType(MealType mealType) {
    String jpql = "SELECT m FROM MealOfCard m WHERE m.mealType = :mealType";
    return em.createQuery(jpql, MealOfCard.class) .setParameter("mealType", mealType).getResultList();
    }
}
