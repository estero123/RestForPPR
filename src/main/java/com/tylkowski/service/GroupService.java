package com.tylkowski.service;

import com.tylkowski.entity.Group;
import org.springframework.data.domain.Pageable;


public interface GroupService {
    void save(Group group);
    void save(Iterable<Group> groupList);
    Iterable<Group> findAll();
    Iterable<Group> findAll(Pageable pageable);
    Group findOne(Long groupId);
    long count();

}
