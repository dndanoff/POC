package io.github.dndanoff.school.domain.ports.in;

import java.util.List;

import io.github.dndanoff.school.domain.model.Teacher;

public interface TeacherOperations {

	List<Teacher> getAllTeachers();

	Teacher createTeacher(Teacher teacher);

	Teacher updateTeacher(Teacher teacher);

	void deleteTeacher(Long id);

}