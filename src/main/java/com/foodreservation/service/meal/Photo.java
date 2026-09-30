package com.foodreservation.service.meal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

//دالة معالجة طلبات GET
@WebServlet("/photo")
public class Photo extends HttpServlet {
    public static final String UPLOAD_PATH = Paths.get(System.getProperty("jboss.home.dir")).getParent() + "/uploadedFiles/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        String imageName = req.getParameter("name");
        File imageFile = new File(UPLOAD_PATH + "/" + imageName);
        if (imageFile.exists()) {
            // محاولة فتح الصورة وقراءتها
           try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
               // إنشاء مصفوفة بايتات بحجم محتوى الصورة
               byte[] buffer = new byte[fileInputStream.available()];
               // قراءة محتويات الصورة في المصفوفة
               fileInputStream.read(buffer);
               resp.getOutputStream().write(buffer);
           }
        }
    }
}