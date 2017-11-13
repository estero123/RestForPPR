package com.tylkowski.service;

import com.tylkowski.entity.Group;
import com.tylkowski.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl  implements GroupService{

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void save(Iterable<Group> groupList) {
        groupRepository.save(groupList);
    }

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Iterable<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }


    @Override
    public Group findOne(Long groupId) {
        return groupRepository.findOne(groupId);
    }

    @Override
    public long count() {
        return groupRepository.count();
    }
}
