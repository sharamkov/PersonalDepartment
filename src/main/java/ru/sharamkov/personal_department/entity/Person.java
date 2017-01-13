package ru.sharamkov.personal_department.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "person")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "person_name", nullable = false)
    private String personName;

    @Column(name = "person_surname", nullable = false)
    private String personSurname;

    @Column(name = "person_patronymic", nullable = false)
    private String personPatronymic;

    @Column(name = "person_date_of_birth", nullable = false)
    private Date personDateOfBirth;

    @Column(name = "person_position", nullable = false)
    private String personPosition;

    @ManyToOne
    @JoinColumn(name = "person_department")
    private Department personDepartment;



    @Column(name = "employment_date", nullable = false)
    private Date employmentDate;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonPatronymic() {
        return personPatronymic;
    }

    public void setPersonPatronymic(String personPatronymic) {
        this.personPatronymic = personPatronymic;
    }

    public Date getPersonDateOfBirth() {
        return personDateOfBirth;
    }

    public void setPersonDateOfBirth(Date personDateOfBirth) {
        this.personDateOfBirth = personDateOfBirth;
    }

    public String getPersonPosition() {
        return personPosition;
    }

    public void setPersonPosition(String personPosition) {
        this.personPosition = personPosition;
    }

    public Department getPersonDepartment() {
        return personDepartment;
    }

    public void setPersonDepartment(Department personDepartment) {
        this.personDepartment = personDepartment;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }


    public String toString(){
        return this.personSurname + " " + this.personName + " " + this.personPatronymic;
    }

}
