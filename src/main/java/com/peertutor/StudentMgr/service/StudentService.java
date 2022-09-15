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

    public StudentDTO getStudentProfile(Long accountId) {
        Student student = studentRepository.findByAccountId(accountId);

        if (student == null) {
            return null;
        }
        StudentDTO result = studentMapper.toDto(student);

        return result;
    }

    public StudentDTO createStudentProfile(StudentProfileReq req) {
        Student student = studentRepository.findByAccountId(req.accountId);

        if (student == null) {
            student = new Student();
        }

        student.setDisplayName(req.displayName);
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
