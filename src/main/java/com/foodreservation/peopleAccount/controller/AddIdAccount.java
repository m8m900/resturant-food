package peopleAccount.controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import peopleAccount.entity.UserEntry;
import peopleAccount.service.UserEntryFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class AddIdAccount extends HttpServlet implements Serializable {
    private UserEntry userEntry;
    private String userId;
    private UserEntry currentUserId;
    private List<UserEntry> userEntries = new ArrayList<>();
    @EJB
    private UserEntryFacade userEntryFacade;

    @PostConstruct
    public void init() {
        userEntry = new UserEntry();
        userEntries = userEntryFacade.findAll();
}
    public void save() {
        userEntryFacade.create(userEntry);
        userEntry =new UserEntry();

    }
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

        public void login1() throws Exception {
        try {
            UserEntry userPeopleEmail = userEntryFacade.getByUsername(userId);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); // تفاصيل الرسبونس
            if (userPeopleEmail.getUserId().equals(userId)) {
                Cookie loginCookie = new Cookie("user",userId); // انشاء كوكي بإسم يوزر وقيمة اليوزرنيم
                //setting cookie to expiry in 1 hour
                loginCookie.setMaxAge(2*60*60);
                response.addCookie(loginCookie); // اضافة الكوكي للرسبونس
                goToPage("/app/order/cartMenu.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "userId خطاء");
            PrimeFaces.current().ajax().update("form:msg");
        }
    }
    public static void goToPage(String redirectUrl) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String contPath = request.getContextPath();
        String redirect = contPath + redirectUrl;
        externalContext.redirect(redirect);
        facesContext.responseComplete();
    }

}
