package meal.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import meal.entity.MealOfCard;
import meal.entity.UploadedFileEntity;
import meal.service.FileUploadService;
import meal.service.MealCardFacade;
import org.omnifaces.util.Faces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class MealCardController implements Serializable {
    @Inject
    private MealCardFacade mealCardFacade;
    private MealOfCard meal_card;
    private List<MealOfCard> meal_cardList = new ArrayList<>();
    private UploadedFileEntity uploadedFileEntity;
    private List<UploadedFileEntity> uploadedFileEntityList = new ArrayList<>();
    public static final String UPLOAD_PATH = Paths.get(System.getProperty("jboss.home.dir")).getParent() + "/uploadedFiles/";
    @Inject
    private FileUploadService fileUploadService;

    @PostConstruct
    public void init() throws IOException {
        Path uploadFolderPath = Paths.get(UPLOAD_PATH);
        if (!Files.exists(uploadFolderPath)) {
            Files.createDirectories(uploadFolderPath);
        }
        String id = Faces.getRequestParameter("id");
        if (id != null) {
            meal_card = mealCardFacade.findById(Long.parseLong(id));
        }
        meal_card = new MealOfCard();
        meal_cardList = mealCardFacade.findAll();
        uploadedFileEntityList = fileUploadService.findAll();
        uploadedFileEntity = new UploadedFileEntity();
    }
    public void saveInDB() {
        mealCardFacade.create(meal_card);
        uploadedFileEntity.setMeal_card(meal_card);
        fileUploadService.create(uploadedFileEntity);
        meal_card = new MealOfCard();

    }
    public void uploadFile(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            Path folder = Paths.get(UPLOAD_PATH);
            Path file2;
            try {
                String fileBaseName = FilenameUtils.getBaseName(file.getFileName());
                String fileExtension = FilenameUtils.getExtension(file.getFileName());
                file2 = Files.createTempFile(folder, fileBaseName, "." + fileExtension);
                InputStream input = file.getInputStream();
                Files.copy(input, file2, StandardCopyOption.REPLACE_EXISTING);
                String filePath = UPLOAD_PATH.concat(file2.getFileName().toString());
                uploadedFileEntityList = List.of(uploadedFileEntity);
                uploadedFileEntity.setFilePath(file2.getFileName().toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uploaded", file.getFileName() + " is uploaded."));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Transactional
    public void deleteMeal_card(MealOfCard meal_card) {
        try{
            mealCardFacade.remove(meal_card); // حذف السجل من قاعدة البيانات
            meal_cardList.remove(meal_card);  // حذف السجل من القائمة
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "تم الحذف", "السجل تم حذفه بنجاح"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "تعذر حذف السجل"));
            e.printStackTrace();
        }
    }
    public List<MealOfCard> filterMealsByType(String Serving_meal) {
        return mealCardFacade.findMealsByType(Serving_meal);
    }

}
