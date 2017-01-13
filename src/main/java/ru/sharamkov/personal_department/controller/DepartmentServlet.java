package ru.sharamkov.personal_department.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.sharamkov.personal_department.exception.PersonalDepartmentException;
import ru.sharamkov.personal_department.logic.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        Service service = (Service) webApplicationContext.getBean("service");

        if (req.getParameter("allDepartments") != null) {

            displayAllDepartments(req, resp, service);

        } else if (req.getParameter("addDepartment") != null) {

            openDepartmentAddingForm(req, resp);

        } else if (req.getParameter("editDepartment") != null) {

            openDepartmentEditForm(req, resp, service);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        Service service = (Service) webApplicationContext.getBean("service");

        if (req.getParameter("deleteDepartment") != null) {

            deleteDepartment(req, resp, service);

        } else if (req.getParameter("departmentOk") != null) {

            String dId = req.getParameter("dId");

            if ("".equals(dId)) {
                addDepartment(req, resp, service);
            } else {
                updateDepartment(req, resp, service);
            }

        }


    }


    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        service.deleteDepartment(Long.parseLong(req.getParameter("dId")));
        resp.sendRedirect("department?allDepartments=Departments");
    }

    private void openDepartmentEditForm(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        Long departmentId = Long.parseLong(req.getParameter("dId"));
        req.setAttribute("department", service.getDepartmentById(departmentId));
        getServletContext().getRequestDispatcher("/Department.jsp").forward(req, resp);
    }

    private void openDepartmentAddingForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Department.jsp").forward(req, resp);
    }

    private void addDepartment(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        addOrUpdateDepartment(req, service);
        resp.sendRedirect("start");
    }

    private void updateDepartment(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        addOrUpdateDepartment(req, service);
        resp.sendRedirect("department?allDepartments=Departments");
    }

    private void addOrUpdateDepartment(HttpServletRequest req, Service service) throws ServletException, IOException {

        Long departmentId = "".equals(req.getParameter("dId")) ? -1L : Long.parseLong(req.getParameter("dId"));
        String departmentName = req.getParameter("dName");
        Integer departmentCodeNumber = Integer.parseInt(req.getParameter("dCode"));

        try {
            service.addOrUpdateDepartment(departmentId, departmentName, departmentCodeNumber);
        } catch (DataIntegrityViolationException e) {
            throw new PersonalDepartmentException("Illegal code duplication: a department with code " + departmentCodeNumber + " already exists");
        }

    }

    private void displayAllDepartments(HttpServletRequest req, HttpServletResponse resp, Service service) throws IOException, ServletException {
        req.setAttribute("departments", service.getAllDepartments());
        getServletContext().getRequestDispatcher("/DepartmentList.jsp").forward(req, resp);
    }


}
