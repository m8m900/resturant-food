package meal.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import meal.entity.MealOfCard;

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

    public List<MealOfCard> findMealsByType(String Serving_meal) {
        String jpql = "SELECT m FROM MealOfCard m WHERE m.Serving_meal = :Serving_meal";
        return em.createQuery(jpql, MealOfCard.class)
                .setParameter("Serving_meal", Serving_meal)
                .getResultList();
    }
    @Transactional
    public List<MealOfCard> findAll() {
        String jpql = "SELECT c FROM MealOfCard c join fetch c.uploadedFileEntities";
        return em.createQuery(jpql, MealOfCard.class).getResultList();
    }

}
