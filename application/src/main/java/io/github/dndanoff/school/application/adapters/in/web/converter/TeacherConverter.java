package io.github.dndanoff.school.application.adapters.in.web.converter;

import org.springframework.stereotype.Component;

import io.github.dndanoff.school.application.adapters.in.web.dto.TeacherDto;
import io.github.dndanoff.school.domain.model.Teacher;

@Component
public class TeacherConverter implements ModelConverter<Teacher, TeacherDto>{
    
    public Teacher dtoToEntity(TeacherDto dto){
        Teacher teacher = new Teacher();
        teacher.setEmail(dto.getEmail());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());

        return teacher;
    }
	
	public TeacherDto entityToDto(Teacher entity){
        TeacherDto dto = new TeacherDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());

        return dto;
    }
}
