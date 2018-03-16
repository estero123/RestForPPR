package com.tylkowski.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Groups")
public class Group extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("gid")
    private long gid;

    @NotNull
    @NotEmpty
    @Column(name = "groupName")
    private String groupName;


    @JsonBackReference
    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private List<Student> students;


    public Group(String groupName) {
        this.groupName = groupName;
    }


    public Group(String groupName, List<Student> students) {
        this.groupName = groupName;
        this.students = students;
    }

    public Group() {
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return getGid() == group.getGid();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getGid());
    }
}
