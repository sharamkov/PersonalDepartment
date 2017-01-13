package ru.sharamkov.personal_department.dao;

import ru.sharamkov.personal_department.entity.Department;

import java.util.List;


public interface DepartmentDAOI {

    public List<Department> getAllDepartments();

    public Department loadDepartmentById(Long id);

    public Department getDepartmentById(Long id);

    public void addOrUpdateDepartment(Department department);

    public void deleteDepartment(Long id);
}
