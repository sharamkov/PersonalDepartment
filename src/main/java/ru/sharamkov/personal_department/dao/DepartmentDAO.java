package ru.sharamkov.personal_department.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sharamkov.personal_department.entity.Department;
import ru.sharamkov.personal_department.exception.PersonalDepartmentException;

import java.util.List;



public class DepartmentDAO implements DepartmentDAOI {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Department> getAllDepartments() {
        Session session = sessionFactory.getCurrentSession();
        List<Department> departments = session.createQuery("from Department").list();
        return departments;
    }


    @Override
    public Department loadDepartmentById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Department department = (Department) session.load(Department.class, id);
        return department;
    }


    @Override
    public Department getDepartmentById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Department department = (Department) session.get(Department.class, id);
        return department;
    }


    @Override
    public void addOrUpdateDepartment(Department department) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(department);
        } catch (ConstraintViolationException e) {
            throw new PersonalDepartmentException("Illegal code duplication: a department with code " + department.getDepartmentCodeNumber() + " already exists");
        }
    }


    @Override
    public void deleteDepartment(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Department.class, id));
    }



}
