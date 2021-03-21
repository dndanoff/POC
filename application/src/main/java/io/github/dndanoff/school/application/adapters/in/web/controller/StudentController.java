package io.github.dndanoff.school.application.adapters.in.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.school.application.adapters.in.web.dto.StudentDto;
import io.github.dndanoff.school.application.adapters.in.web.facade.StudentRestFacade;
import io.github.dndanoff.school.application.adapters.in.web.validation.RestPrecondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/students")
class StudentController {

    private final StudentRestFacade studentFacade;

    @Autowired
    StudentController(StudentRestFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<StudentDto> getStudents() {
        log.info("Received request to getStudents()");
        return studentFacade.getAllStudents();
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    StudentDto createStudent(@RequestBody @Valid StudentDto student) {
        log.info("Received request to createStudent() with params: student={}", student);
        RestPrecondition.checkRequestState(student.getId() == null);
        return studentFacade.createStudent(student);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    StudentDto updateStudent(@RequestBody @Valid StudentDto student) {
        log.info("Received request to updateStudent() with params: student={}", student);
        RestPrecondition.checkRequestState(student.getId() != null);
        return studentFacade.updateStudent(student);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteStudent(@PathVariable Long id) {
        log.info("Received request to deleteStudent() with params: id={}", id);
        RestPrecondition.checkNotNull(id);
        studentFacade.deleteStudent(id);
    }
}