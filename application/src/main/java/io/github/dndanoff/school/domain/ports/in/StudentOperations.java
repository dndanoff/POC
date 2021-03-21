package io.github.dndanoff.school.domain.ports.in;

import java.util.List;

import io.github.dndanoff.school.domain.model.Student;

public interface StudentOperations {

	List<Student> getAllStudents();

	Student createStudent(Student student);

	Student updateStudent(Student student);

	void deleteStudent(Long id);

}