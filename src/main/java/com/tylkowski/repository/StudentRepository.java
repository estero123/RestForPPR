package com.tylkowski.repository;

import com.tylkowski.entity.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends CrudRepository<Student, Long> {
}
