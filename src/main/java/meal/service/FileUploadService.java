package meal.service;

import AbstractFacade.AbstractFacade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import meal.entity.UploadedFileEntity;

import java.util.List;

@Stateless
public class FileUploadService extends AbstractFacade<UploadedFileEntity> {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public FileUploadService() {
        super(UploadedFileEntity.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
