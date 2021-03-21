package io.github.dndanoff.school.application.adapters.in.web.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.dndanoff.school.application.adapters.in.web.converter.ModelConverter;
import io.github.dndanoff.school.application.adapters.in.web.dto.TeacherDto;
import io.github.dndanoff.school.domain.model.Teacher;
import io.github.dndanoff.school.domain.ports.in.TeacherOperations;

@Component
public class TeacherRestFacade {
	
    private final TeacherOperations teacherOperations;
    private final ModelConverter<Teacher, TeacherDto> converter;

    @Autowired
    public TeacherRestFacade(TeacherOperations teacherOperations, ModelConverter<Teacher, TeacherDto> converter){
        this.teacherOperations = teacherOperations;
        this.converter = converter;
    }

    public List<TeacherDto> getAllTeachers(){
        return converter.entityToDto(teacherOperations.getAllTeachers());
    }

    public TeacherDto createTeacher(TeacherDto teacher){
        Teacher entity = converter.dtoToEntity(teacher);
        return converter.entityToDto(teacherOperations.createTeacher(entity));
    }

    public TeacherDto updateTeacher(TeacherDto teacher){
        Teacher entity = converter.dtoToEntity(teacher);
        return converter.entityToDto(teacherOperations.updateTeacher(entity));
    }

    public void deleteTeacher(Long id){
    	teacherOperations.deleteTeacher(id);
    }
}
