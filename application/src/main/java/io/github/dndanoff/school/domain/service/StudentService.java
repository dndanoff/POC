package io.github.dndanoff.school.domain.service;

import java.util.ArrayList;
import java.util.List;

import io.github.dndanoff.school.domain.model.Student;
import io.github.dndanoff.school.domain.ports.in.StudentOperations;
import io.github.dndanoff.school.domain.ports.out.StudentRepository;

public class StudentService implements StudentOperations {

    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    @Override
	public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(s -> students.add(s));

        return students;
    }

    @Override
	public Student createStudent(Student student){
        return studentRepo.save(student);
    }

    @Override
	public Student updateStudent(Student student){
        return studentRepo.save(student);
    }

    @Override
	public void deleteStudent(Long id){
        studentRepo.deleteById(id);
    }
}
