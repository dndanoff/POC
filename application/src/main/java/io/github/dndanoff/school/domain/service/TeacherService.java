package io.github.dndanoff.school.domain.service;

import java.util.ArrayList;
import java.util.List;

import io.github.dndanoff.school.domain.model.Teacher;
import io.github.dndanoff.school.domain.ports.in.TeacherOperations;
import io.github.dndanoff.school.domain.ports.out.TeacherRepository;

public class TeacherService implements TeacherOperations {
    
    private final TeacherRepository teacherRepo;

    public TeacherService(TeacherRepository teacherRepo){
        this.teacherRepo = teacherRepo;
    }
    
    @Override
	public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        teacherRepo.findAll().forEach(s -> teachers.add(s));

        return teachers;
    }

    @Override
	public Teacher createTeacher(Teacher teacher){
        return teacherRepo.save(teacher);
    }

    @Override
	public Teacher updateTeacher(Teacher teacher){
        return teacherRepo.save(teacher);
    }

    @Override
	public void deleteTeacher(Long id){
        teacherRepo.deleteById(id);
    }
}
