package logIn.controller;

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
import logIn.entity.UserPeopleEmail;
import logIn.service.LogInFacade;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class UserController extends HttpServlet implements Serializable {
    private UserPeopleEmail userPeopleEmail;
    private String username;
    private String password;
    private UserPeopleEmail currentUserPeopleEmail;
    private List<UserPeopleEmail> userPeopleEmailList = new ArrayList<>();
    @EJB
    LogInFacade logInFacade;
    boolean isAdmin;

    @PostConstruct
    public void init() {

        userPeopleEmail = new UserPeopleEmail();
        userPeopleEmailList = logInFacade.findAll();
    }

    public void save() {
        logInFacade.create(userPeopleEmail);
        userPeopleEmail =new UserPeopleEmail();

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "الحفظ", "تم الحفظ"));
    }
    public void info3() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "عدم الحفظ", "عدم الحفظ"));
    }

    public void login() throws Exception {
        try {
            UserPeopleEmail userPeopleEmail = logInFacade.getByUsername(username);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); // تفاصيل الرسبونس
            if (userPeopleEmail.getPassword().equals(password)) {
                Cookie loginCookie = new Cookie("user",username); // انشاء كوكي بإسم يوزر وقيمة اليوزرنيم
                //setting cookie to expiry in 1 hour
                loginCookie.setMaxAge(2*60*60);
                response.addCookie(loginCookie); // اضافة الكوكي للرسبونس
                goToPage("/app/userAcount/addUserId.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "كلمة المرور خطأ");
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
