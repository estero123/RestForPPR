package com.tylkowski.service;

import com.tylkowski.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import java.util.Optional;


public interface GroupService {
    Group save(Group group);
    void save(Iterable<Group> groupList);
    Iterable<Group> findAll();
    Page<Group> findAll(Pageable pageable);
    Optional<Group> findOne(long groupId);
    void deleteById(long groupId);
    long count();

}
