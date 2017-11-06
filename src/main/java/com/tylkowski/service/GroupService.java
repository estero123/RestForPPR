package com.tylkowski.service;

import com.tylkowski.entity.Group;

public interface GroupService {
    void save(Group group);
    void save(Iterable<Group> groupList);
    Iterable<Group> findAll();
    Group findOne(Long groupId);

}
