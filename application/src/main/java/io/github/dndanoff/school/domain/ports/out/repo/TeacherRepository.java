package io.github.dndanoff.school.domain.ports.out.repo;

import java.util.Optional;

import io.github.dndanoff.school.domain.model.Teacher;

public interface TeacherRepository {
	Teacher save(Teacher entity);
	Optional<Teacher> findById(Long id);
	Iterable<Teacher> findAll();
	long count();
	void deleteById(Long id);
}
