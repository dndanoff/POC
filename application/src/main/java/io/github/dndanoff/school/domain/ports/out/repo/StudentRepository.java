package io.github.dndanoff.school.domain.ports.out.repo;

import java.util.Optional;

import io.github.dndanoff.school.domain.model.Student;

public interface StudentRepository {
	Student save(Student entity);
	Optional<Student> findById(Long id);
	Iterable<Student> findAll();
	long count();
	void deleteById(Long id);
}
