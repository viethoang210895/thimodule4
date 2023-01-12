package com.codegym.model;

import com.codegym.model.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getStudents() {
        return employees;
    }

    public void setStudents(List<Employee> employees) {
        this.employees = employees;
    }

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    @OneToMany(targetEntity = Employee.class)
    private List<Employee> employees;

}
