package com.tylkowski.service;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface GroupService {
    Group save(Group group);

    void save(Iterable<Group> groupList);

    Iterable<Group> findAll();

    Page<Group> findAll(Pageable pageable);

    Optional<Group> findById(long groupId);

    void deleteById(long groupId);

    void delete(Group group);

    int update(Group student);

    long count();

}
