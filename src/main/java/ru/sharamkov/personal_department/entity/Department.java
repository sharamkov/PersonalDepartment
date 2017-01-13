package ru.sharamkov.personal_department.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_code_number", nullable = false, unique = true)
    private Integer departmentCodeNumber;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personDepartment")
    private List<Person> persons;


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Integer getDepartmentCodeNumber() {
        return departmentCodeNumber;
    }

    public void setDepartmentCodeNumber(Integer departmentCodeNumber) {
        this.departmentCodeNumber = departmentCodeNumber;
    }

    public String toString(){
        return "Название отдела: " + this.departmentName + ", код отдела: " + this.departmentCodeNumber;
    }
}

