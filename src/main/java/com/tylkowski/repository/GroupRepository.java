package com.tylkowski.repository;

import com.tylkowski.entity.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}
