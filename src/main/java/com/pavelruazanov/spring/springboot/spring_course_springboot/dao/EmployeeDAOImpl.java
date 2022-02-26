package com.pavelruazanov.spring.springboot.spring_course_springboot.dao;

import com.pavelruazanov.spring.springboot.spring_course_springboot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;
    @Override

    public List<Employee> getAllEmployees() {

//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("from Employee",Employee.class);
//        List<Employee> allEmployees = query.getResultList();

        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Employee employee1 = entityManager.merge(employee);
        employee.setId(employee1.getId());


//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Employee employee = entityManager.find(Employee.class,id);

//        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        Query query = entityManager.createQuery("delete from Employee " +
                "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();

//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee " +
//                "where id = :employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
    }
}
