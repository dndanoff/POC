package io.github.dndanoff.school.application.adapters.in.web.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.dndanoff.school.application.adapters.in.web.converter.ModelConverter;
import io.github.dndanoff.school.application.adapters.in.web.dto.StudentDto;
import io.github.dndanoff.school.domain.model.Student;
import io.github.dndanoff.school.domain.ports.in.StudentOperations;

@Component
public class StudentRestFacade {

    private final StudentOperations studentOperations;
    private final ModelConverter<Student, StudentDto> converter;

    @Autowired
    public StudentRestFacade(StudentOperations studentOperations, ModelConverter<Student, StudentDto> converter){
        this.studentOperations = studentOperations;
        this.converter = converter;
    }

    public List<StudentDto> getAllStudents(){
        return converter.entityToDto(studentOperations.getAllStudents());
    }

    public StudentDto createStudent(StudentDto student){
        Student entity = converter.dtoToEntity(student);
        return converter.entityToDto(studentOperations.createStudent(entity));
    }

    public StudentDto updateStudent(StudentDto student){
        Student entity = converter.dtoToEntity(student);
        return converter.entityToDto(studentOperations.updateStudent(entity));
    }

    public void deleteStudent(Long id){
    	studentOperations.deleteStudent(id);
    }
}
