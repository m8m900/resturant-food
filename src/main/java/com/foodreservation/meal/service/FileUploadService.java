package meal.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import meal.entity.UploadedFileEntity;

import java.util.List;

import abstractFacade.AbstractFacade;

@Stateless
public class FileUploadService extends AbstractFacade<UploadedFileEntity> {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public FileUploadService() {
        super(UploadedFileEntity.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
