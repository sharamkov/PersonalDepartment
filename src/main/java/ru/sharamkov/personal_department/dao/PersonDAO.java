package ru.sharamkov.personal_department.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sharamkov.personal_department.entity.Person;

import java.util.List;


public class PersonDAO implements PersonDAOI {


    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }


    @Override
    public List<Person> getAllPersons() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons = session.createQuery("from Person order by personSurname").list();
        return persons;
    }


    @Override
    public List<Person> getPersonsByDepartmentId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Person p where " +
                "p.personDepartment.departmentId = ? order by p.personSurname");
        query.setLong(0, id);
        List<Person> persons = query.list();
        return persons;
    }


    @Override
    public List<Person> getPersonsBySurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons = session.createQuery("from Person p where p.personSurname " +
                "like :surname order by p.personSurname").setString("surname", surname + "%").list();
        return persons;

    }


    @Override
    public List<Person> getPersonsByDepartmentIdAndPersonSurname(Long id, String surname) {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons = session.createQuery("from Person p where p.personSurname " +
                "like :surname AND p.personDepartment.departmentId = :id order by p.personSurname")
                .setString("surname", surname + "%").setLong("id", id).list();
        return persons;
    }


    @Override
    public Person getPersonById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, id);
        return person;
    }


    @Override
    public void addPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }


    @Override
    public void deletePerson(Long personId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Person.class, personId));
    }





}
