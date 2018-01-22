package com.tylkowski.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Students")
@JsonIdentityInfo(scope = Student.class,generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

//    @ManyToMany(mappedBy = "students",fetch = FetchType.EAGER)
//    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "groups_students",
        joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "groups_id",
                referencedColumnName = "id"))
    private List<Group> groups;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, List<Group> groups) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = groups;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }



}
