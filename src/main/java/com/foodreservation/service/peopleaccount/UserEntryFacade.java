package com.foodreservation.service.peopleaccount;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.foodreservation.model.login.UserPeopleEmail;
import com.foodreservation.model.peopleaccount.UserEntry;
import com.foodreservation.service.AbstractFacade;

@Stateless
public class UserEntryFacade extends AbstractFacade<UserEntry> {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public UserEntryFacade() {
        super(UserEntry.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }


    public UserEntry getByUsername(String userId){
        Query query=  em.createQuery("select r from UserEntry r where r.userId = :id");
        query.setParameter("id",userId);
        if (query.getResultList().isEmpty()){
            return new UserEntry();
        }
        else
            return (UserEntry) query.getSingleResult();

    }

}
