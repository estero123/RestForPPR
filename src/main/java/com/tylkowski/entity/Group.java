package com.tylkowski.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "groupName")
    private String groupName;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "students_groups",
//            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "groups_id",
//                    referencedColumnName = "id"))
//    @JsonBackReference
    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Student> students;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
