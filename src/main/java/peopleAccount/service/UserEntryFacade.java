package peopleAccount.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import logIn.entity.UserPeopleEmail;
import peopleAccount.entity.UserEntry;

@Stateless
public class UserEntryFacade extends AbstractFacade<UserEntry> {

    @PersistenceContext(unitName = "default")
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
