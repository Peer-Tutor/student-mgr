package com.peertutor.StudentMgr.repository;

import com.peertutor.StudentMgr.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
    Student findById(long id);
}

