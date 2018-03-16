package com.tylkowski.service;

import com.tylkowski.entity.Group;
import com.tylkowski.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tylkowski.utils.Constants.NOT_EXISTS;
import static com.tylkowski.utils.Constants.OK;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void save(Iterable<Group> groupList) {
        groupRepository.saveAll(groupList);
    }

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Optional<Group> findById(long groupId) {
        return groupRepository.findById(groupId);
    }

    @Override
    public void deleteById(long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public long count() {
        return groupRepository.count();
    }

    @Override
    public int update(Group group) {
        Optional<Group> groupFromDb = groupRepository.findById(group.getGid());
        if(groupFromDb.isPresent()) {
            groupRepository.save(group);
            return OK;
        }
        System.out.println("Group with gid" + group.getGid() + " doesn't exists.");
        return NOT_EXISTS;
    }
}
