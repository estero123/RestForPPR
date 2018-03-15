package com.tylkowski.repository;

import com.tylkowski.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.hateoas.Link;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}
