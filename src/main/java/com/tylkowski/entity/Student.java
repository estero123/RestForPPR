package com.tylkowski.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Students")
public class Student extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("sid")
    private long sid;

    @Column(name = "firstName")
    @NotNull
    @NotEmpty
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @NotEmpty
    private String lastName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "groups_students",
            joinColumns = @JoinColumn(name = "students_sid", referencedColumnName = "sid"),
            inverseJoinColumns = @JoinColumn(name = "groups_gid", referencedColumnName = "gid"))
    private List<Group> groups;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, List<Group> groups) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = groups;
    }


    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
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

    @JsonIgnore
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getSid() == student.getSid();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getSid());
    }
}
