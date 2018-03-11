package com.tylkowski.entity;
import com.tylkowski.entity.Group;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Students")
public class Student extends ResourceSupport{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("sid")
    private long sid;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
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
}
