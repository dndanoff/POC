package io.github.dndanoff.school.application.adapters.in.web.converter;

import org.springframework.stereotype.Component;

import io.github.dndanoff.school.application.adapters.in.web.dto.StudentDto;
import io.github.dndanoff.school.domain.model.Student;

@Component
public class StudentConverter implements ModelConverter<Student, StudentDto>{
    public Student dtoToEntity(StudentDto dto){
        return null;
    }
	
	public StudentDto entityToDto(Student entity){
        return null;
    }
}
