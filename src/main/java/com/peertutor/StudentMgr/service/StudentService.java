package com.peertutor.StudentMgr.service;

import com.peertutor.StudentMgr.model.Student;
import com.peertutor.StudentMgr.model.viewmodel.request.StudentProfileReq;
import com.peertutor.StudentMgr.repository.StudentRepository;
import com.peertutor.StudentMgr.service.dto.StudentDTO;
import com.peertutor.StudentMgr.service.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private final StudentMapper studentMapper;
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO getStudentProfile(String accountName) {
        Student student = studentRepository.findByAccountName(accountName);

        if (student == null) {
            return null;
        }
        StudentDTO result = studentMapper.toDto(student);

        return result;
    }

    public StudentDTO createStudentProfile(StudentProfileReq req) {
        Student student = studentRepository.findByAccountName(req.name);

        if (student == null) {
            student = new Student();
            student.setAccountName(req.name);
        }

        if (req.displayName != null && !req.displayName.trim().isEmpty()) {
            student.setDisplayName(req.displayName);
        } else {
            student.setDisplayName(req.name);
        }

        student.setIntroduction(req.introduction);
        student.setSubjects(req.subjects);

        try {
            student = studentRepository.save(student);
        } catch (Exception e) {
            logger.error("Student Profile Creation Failed: " + e.getMessage());
            return null;
        }

        StudentDTO result = studentMapper.toDto(student);

        return result;
    }
}
