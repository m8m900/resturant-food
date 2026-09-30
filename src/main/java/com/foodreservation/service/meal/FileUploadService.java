package com.foodreservation.service.meal;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.foodreservation.model.meal.UploadedFileEntity;
import com.foodreservation.service.AbstractFacade;

import java.util.List;

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
