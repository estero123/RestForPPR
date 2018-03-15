package com.tylkowski.repository;

import com.tylkowski.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}
