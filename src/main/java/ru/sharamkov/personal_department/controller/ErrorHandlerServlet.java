package ru.sharamkov.personal_department.controller;

import ru.sharamkov.personal_department.exception.PersonalDepartmentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ErrorHandlerServlet extends HttpServlet {


    private void handleError(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        PersonalDepartmentException exception = (PersonalDepartmentException) req.getAttribute("javax.servlet.error.exception");
        req.setAttribute("message", exception.getMessage());
        getServletContext().getRequestDispatcher("/Error.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        handleError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        handleError(req, resp);
    }
}
