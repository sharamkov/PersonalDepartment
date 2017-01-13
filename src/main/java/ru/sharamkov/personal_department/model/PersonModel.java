package ru.sharamkov.personal_department.model;

import ru.sharamkov.personal_department.entity.Department;
import ru.sharamkov.personal_department.entity.Person;
import java.util.*;


public class PersonModel {

    private Long personId;
    private String personSurname;
    private String personName;
    private String personPatronymic;
    private List<Integer> personDateOfBirth;
    private String personPosition;
    private List<Integer> employmentDate;
    private Department personDepartment;
    private List<Department> departments;


    public void init(Person person) {
        this.personId = person.getPersonId();
        this.personSurname = person.getPersonSurname();
        this.personName = person.getPersonName();
        this.personPatronymic = person.getPersonPatronymic();
        this.personDateOfBirth = dateProcessor(person.getPersonDateOfBirth());
        this.personPosition = person.getPersonPosition();
        this.employmentDate = dateProcessor(person.getEmploymentDate());
        this.personDepartment = person.getPersonDepartment();

    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }


    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public String getPersonPatronymic() {
        return personPatronymic;
    }

    public void setPersonPatronymic(String personPatronymic) {
        this.personPatronymic = personPatronymic;
    }


    public String getPersonPosition() {
        return personPosition;
    }

    public void setPersonPosition(String personPosition) {
        this.personPosition = personPosition;
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }


    public Department getPersonDepartment() {
        return personDepartment;
    }

    public void setPersonDepartment(Department personDepartment) {
        this.personDepartment = personDepartment;
    }


    public List<Integer> getPersonDateOfBirth() {
        return personDateOfBirth;
    }

    public void setPersonDateOfBirth(List<Integer> personDateOfBirth) {
        this.personDateOfBirth = personDateOfBirth;
    }


    public List<Integer> getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(List<Integer> employmentDate) {
        this.employmentDate = employmentDate;
    }


    private List<Integer> dateProcessor(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        List<Integer> calendarDate = new ArrayList<Integer>();
        calendarDate.add(calendar.get(Calendar.DAY_OF_MONTH));
        calendarDate.add(calendar.get(Calendar.MONTH) + 1);
        calendarDate.add(calendar.get(Calendar.YEAR));

        return calendarDate;
    }
}
