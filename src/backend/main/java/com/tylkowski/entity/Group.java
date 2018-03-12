package com.tylkowski.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@ToString
@Entity
@Table(name = "Groups")
public class Group extends ResourceSupport{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("gid")
    private long gid;

    @NotNull
    @Column(name = "groupName")
    private String groupName;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "students_groups",
//            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "sid"),
//            inverseJoinColumns = @JoinColumn(name = "groups_id",
//                    referencedColumnName = "sid"))
//    @JsonBackReference
    @JsonBackReference
    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    private List<Student> students;


    public Group(String groupName) {
        this.groupName = groupName;
    }


    public Group(String groupName,List<Student> students) {
        this.groupName = groupName;
        this.students = students;
    }

    public Group() {
    }
}