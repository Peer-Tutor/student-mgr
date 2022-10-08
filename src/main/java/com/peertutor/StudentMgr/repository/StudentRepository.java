package com.peertutor.StudentMgr.repository;

import com.peertutor.StudentMgr.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByAccountName(String accountName);

}

