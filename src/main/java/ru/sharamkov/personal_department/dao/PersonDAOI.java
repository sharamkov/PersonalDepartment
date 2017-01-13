package ru.sharamkov.personal_department.dao;

import ru.sharamkov.personal_department.entity.Person;

import java.util.List;


public interface PersonDAOI {

    public List<Person> getAllPersons();

    public List<Person> getPersonsByDepartmentId(Long id);

    public List<Person> getPersonsBySurname(String name);

    public List<Person> getPersonsByDepartmentIdAndPersonSurname(Long id, String surname);

    public Person getPersonById(Long id);

    public Person loadPersonById(Long id);

    public void addPerson(Person person);

    public void deletePerson(Person person);





}
