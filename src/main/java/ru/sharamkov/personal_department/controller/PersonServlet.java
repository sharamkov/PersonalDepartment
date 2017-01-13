package ru.sharamkov.personal_department.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.sharamkov.personal_department.entity.Person;
import ru.sharamkov.personal_department.logic.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        Service service = (Service) webApplicationContext.getBean("service");

        if (req.getParameter("allPersons") != null) {

            displayAllPersons(req, resp, service);

        } else if (req.getParameter("addPerson") != null) {

            openPersonAddingForm(req, resp, service);

        } else if (req.getParameter("search") != null) {

            searchPersons(req, resp, service);

        } else if (req.getParameter("pId") != null && req.getParameter("editPerson") != null) {

            openPersonEditForm(req, resp, service);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        Service service = (Service) webApplicationContext.getBean("service");

        if (req.getParameter("pId") != null && req.getParameter("deletePerson") != null) {

            deletePerson(req, resp, service);

        } else if (req.getParameter("personOk") != null) {

            if ("-1".equals(req.getParameter("pId"))) {
                addPerson(req, resp, service);
            } else {
                updatePerson(req, resp, service);
            }

        }


    }


    private void deletePerson(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        long personId = Long.parseLong(req.getParameter("pId"));
        service.deletePerson(personId);
        sendRedirect(req, resp);
    }


    private void openPersonEditForm(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        long personId = Long.parseLong(req.getParameter("pId"));
        req.setAttribute("personModel", service.getPersonModel(personId));
        getServletContext().getRequestDispatcher("/Person.jsp").forward(req, resp);
    }

    private void openPersonAddingForm(HttpServletRequest req, HttpServletResponse resp, Service service) throws IOException, ServletException {
        req.setAttribute("personModel", service.getPersonModel(-1L));
        getServletContext().getRequestDispatcher("/Person.jsp").forward(req, resp);
    }


    private void addPerson(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        addOrUpdatePerson(req, service);
        resp.sendRedirect("start");
    }

    private void updatePerson(HttpServletRequest req, HttpServletResponse resp, Service service) throws ServletException, IOException {
        addOrUpdatePerson(req, service);
        sendRedirect(req, resp);
    }


    private void addOrUpdatePerson(HttpServletRequest req, Service service) throws ServletException, IOException {
        Long personId = "".equals(req.getParameter("pId")) ? -1L : Long.parseLong(req.getParameter("pId"));
        String personSurname = req.getParameter("pSurname");
        String personName = req.getParameter("pName");
        String personPatronymic = req.getParameter("pPatronymic");
        int[] personDateOfBirth = {Integer.parseInt(req.getParameter("bDay")), Integer.parseInt(req.getParameter("bMonth")), Integer.parseInt(req.getParameter("bYear"))};
        int[] employmentDate = {Integer.parseInt(req.getParameter("empDay")), Integer.parseInt(req.getParameter("empMonth")), Integer.parseInt(req.getParameter("empYear"))};
        String personPosition = req.getParameter("pPositon");
        Long personDepartmentId = Long.parseLong(req.getParameter("pDepartmentId"));

        service.addOrUpdatePerson(personId, personSurname, personName, personPatronymic, personPosition,
                personDateOfBirth, employmentDate, personDepartmentId);
    }


    private void displayAllPersons(HttpServletRequest req, HttpServletResponse resp, Service service) throws IOException, ServletException {
        List<Person> personList = service.getPersons(-1L, "");
        req.setAttribute("personList", personList);
        getServletContext().getRequestDispatcher("/PersonList.jsp").forward(req, resp);
    }


    private void sendRedirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String location = "person?searchedDepId=" + req.getParameter("searchedDepId") + "&searchedSurname=" + URLEncoder.encode(req.getParameter("searchedSurname"), "UTF-8") + "&search=Search";
        resp.sendRedirect(location);

    }


    private void searchPersons(HttpServletRequest req, HttpServletResponse resp, Service service) throws IOException, ServletException {
        Long departmentId = "".equals(req.getParameter("searchedDepId")) ? -1L : Long.parseLong(req.getParameter("searchedDepId"));
        String surname = req.getParameter("searchedSurname");

        List<Person> personList = service.getPersons(departmentId, surname);

        req.setAttribute("personList", personList);

        getServletContext().getRequestDispatcher("/PersonList.jsp").forward(req, resp);
    }


}
