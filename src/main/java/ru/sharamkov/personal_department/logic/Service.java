package ru.sharamkov.personal_department.logic;

import org.springframework.transaction.annotation.Transactional;
import ru.sharamkov.personal_department.dao.DepartmentDAOI;
import ru.sharamkov.personal_department.dao.PersonDAOI;
import ru.sharamkov.personal_department.entity.Department;
import ru.sharamkov.personal_department.entity.Person;
import ru.sharamkov.personal_department.exception.PersonalDepartmentException;
import ru.sharamkov.personal_department.model.PersonModel;

import java.util.Calendar;
import java.util.List;


public class Service {

    private DepartmentDAOI departmentDAOI;

    private PersonDAOI personDAOI;


    public void setDepartmentDAOI(DepartmentDAOI departmentDAOI) {
        this.departmentDAOI = departmentDAOI;
    }

    public void setPersonDAOI(PersonDAOI personDAOI) {
        this.personDAOI = personDAOI;
    }


    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentDAOI.getAllDepartments();
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(Long departmentId) {
        return departmentDAOI.getDepartmentById(departmentId);
    }


    @Transactional(readOnly = true)
    public List<Person> getPersons(Long departmentId, String personSurname) {

        if (departmentId == -1L) {

            if (personSurname.isEmpty()) {
                return personDAOI.getAllPersons();
            } else {
                return personDAOI.getPersonsBySurname(personSurname);
            }

        } else {

            if (personSurname.isEmpty()) {
                return personDAOI.getPersonsByDepartmentId(departmentId);
            } else {
                return personDAOI.getPersonsByDepartmentIdAndPersonSurname(departmentId, personSurname);
            }
        }

    }

    @Transactional(readOnly = true)
    public PersonModel getPersonModel(Long id) {

        PersonModel personModel = new PersonModel();

        if (id != -1L) {
            Person person = personDAOI.getPersonById(id);
            personModel.init(person);
        } else {
            personModel.setPersonId(-1L);
        }

        List<Department> departments = departmentDAOI.getAllDepartments();

        if (departments.isEmpty()) {
            throw new PersonalDepartmentException("Add at least one department before person adding");
        }
        personModel.setDepartments(departments);

        return personModel;

    }

    @Transactional
    public void addOrUpdatePerson(Long personId, String personSurname, String personName, String personPatronymic,
                                  String personPosition, int[] personDateOfBirth, int[] employmentDate, Long personDepartmentId) {

        Person person;
        Calendar calendar = Calendar.getInstance();

        if (personId != -1L) {
            person = personDAOI.loadPersonById(personId);
        } else {
            person = new Person();
        }

        person.setPersonSurname(personSurname);
        person.setPersonName(personName);
        person.setPersonPatronymic(personPatronymic);
        person.setPersonPosition(personPosition);
        calendar.set(personDateOfBirth[2], personDateOfBirth[1] - 1, personDateOfBirth[0]);
        person.setPersonDateOfBirth(calendar.getTime());
        calendar.set(employmentDate[2], employmentDate[1] - 1, employmentDate[0]);
        person.setEmploymentDate(calendar.getTime());
        person.setPersonDepartment(departmentDAOI.loadDepartmentById(personDepartmentId));

        if (personId == -1L) {
            personDAOI.addPerson(person);
        }


    }

    @Transactional
    public void addOrUpdateDepartment(Long departmentId, String departmentName, Integer departmentCodeNumber) {

        Department department;

        if (departmentId != -1L) {
            department = departmentDAOI.loadDepartmentById(departmentId);
        } else {
            department = new Department();
        }

        department.setDepartmentName(departmentName);
        department.setDepartmentCodeNumber(departmentCodeNumber);

        if (departmentId == -1L) {
            departmentDAOI.addDepartment(department);
        }


    }

    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = departmentDAOI.loadDepartmentById(departmentId);
        departmentDAOI.deleteDepartment(department);
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = personDAOI.loadPersonById(id);
        personDAOI.deletePerson(person);
    }

}
