package com.foodreservation.config;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.foodreservation.model.login.UserPeopleEmail;
import com.foodreservation.service.login.LogInFacade;


@WebListener
public class AccountAuto implements ServletContextListener {
    @EJB
    LogInFacade logInFacade;
    private final UserPeopleEmail userPeopleEmail = new UserPeopleEmail();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize application data
        if (logInFacade.findAll().isEmpty()){
            userPeopleEmail.setName("admin");
            userPeopleEmail.setPassword("123");
            logInFacade.create(userPeopleEmail);
        }}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup code if needed
    }
}
