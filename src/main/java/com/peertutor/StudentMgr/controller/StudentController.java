package com.peertutor.StudentMgr.controller;

import com.peertutor.StudentMgr.model.viewmodel.request.StudentProfileReq;
import com.peertutor.StudentMgr.model.viewmodel.response.StudentProfileRes;
import com.peertutor.StudentMgr.repository.StudentRepository;
import com.peertutor.StudentMgr.service.AuthService;
import com.peertutor.StudentMgr.service.StudentService;
import com.peertutor.StudentMgr.service.dto.StudentDTO;
import com.peertutor.StudentMgr.util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/student-mgr")
public class StudentController {
    @Autowired
    AppConfig appConfig;
    @Autowired
    private StudentRepository studentRepository;// = new CustomerRepository();
    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthService authService;

    @GetMapping(path = "/health")
    public @ResponseBody String healthCheck() {
        return "Ok 2";
    }

    @PostMapping(path = "/student")
    public @ResponseBody ResponseEntity<StudentProfileRes> createStudentProfile(@RequestBody @Valid StudentProfileReq req) {
        boolean result = authService.getAuthentication(req.name, req.sessionToken);
        if (!result) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        StudentDTO savedUser;

        savedUser = studentService.createStudentProfile(req);

        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        StudentProfileRes res = new StudentProfileRes();
        res.displayName = savedUser.getDisplayName();
        res.introduction = savedUser.getIntroduction();
        res.subjects = savedUser.getSubjects();
        res.id = savedUser.getId();

        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/student")
    public @ResponseBody ResponseEntity<StudentProfileRes> getStudentProfile(@RequestBody @Valid StudentProfileReq req) {
        boolean result = authService.getAuthentication(req.name, req.sessionToken);
        if (!result) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        StudentDTO studentRetrieved;
        studentRetrieved = studentService.getStudentProfile(req.accountName);

        if (studentRetrieved == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        StudentProfileRes res = new StudentProfileRes();
        res.displayName = studentRetrieved.getDisplayName();
        res.introduction = studentRetrieved.getIntroduction();
        res.subjects = studentRetrieved.getSubjects();
        res.id = studentRetrieved.getId();

        return ResponseEntity.ok().body(res);
    }

}
