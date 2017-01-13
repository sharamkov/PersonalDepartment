package ru.sharamkov.personal_department.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.sharamkov.personal_department.entity.Department;
import ru.sharamkov.personal_department.logic.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class StartFrameServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        Service service = (Service) webApplicationContext.getBean("service");


        List<Department> departments = service.getAllDepartments();
        req.setAttribute("departments", departments);
        getServletContext().getRequestDispatcher("/StartFrame.jsp").forward(req, resp);
    }


}
