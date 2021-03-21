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

import io.github.dndanoff.school.application.adapters.in.web.dto.TeacherDto;
import io.github.dndanoff.school.application.adapters.in.web.facade.TeacherRestFacade;
import io.github.dndanoff.school.application.adapters.in.web.validation.RestPrecondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/teachers")
class TeacherController {

    private final TeacherRestFacade teacherFacade;

    @Autowired
    TeacherController(TeacherRestFacade teacherFacade){
        this.teacherFacade = teacherFacade;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TeacherDto> getTeachers() {
        log.info("Received request to getTeachers()");
        return teacherFacade.getAllTeachers();
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    TeacherDto createStudent(@RequestBody @Valid TeacherDto teacher) {
        log.info("Received request to createStudent() with params: teacher={}", teacher);
        RestPrecondition.checkRequestState(teacher.getId() == null);
        return teacherFacade.createTeacher(teacher);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    TeacherDto updateTeacher(@RequestBody @Valid TeacherDto teacher) {
        log.info("Received request to updateTeacher() with params: teacher={}", teacher);
        RestPrecondition.checkRequestState(teacher.getId() != null);
        return teacherFacade.updateTeacher(teacher);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTeacher(@PathVariable Long id) {
        log.info("Received request to deleteTeacher() with params: id={}", id);
        RestPrecondition.checkNotNull(id);
        teacherFacade.deleteTeacher(id);
    }
}
