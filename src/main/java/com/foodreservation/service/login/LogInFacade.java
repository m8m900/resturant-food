package com.foodreservation.service.login;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.foodreservation.model.login.UserPeopleEmail;
import com.foodreservation.service.AbstractFacade;

@Stateless
public class LogInFacade extends AbstractFacade<UserPeopleEmail> {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    public LogInFacade() {
        super(UserPeopleEmail.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public UserPeopleEmail getByUsername(String username){
        Query query=  em.createQuery("select r from UserPeopleEmail r where r.name = :name");
        query.setParameter("name",username);
        if (query.getResultList().size()==0){

            return new UserPeopleEmail();
        }
        else
            return (UserPeopleEmail) query.getSingleResult();

    }

}
