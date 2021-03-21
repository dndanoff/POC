package io.github.dndanoff.school.application.adapters.out;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.school.domain.model.Student;
import io.github.dndanoff.school.domain.model.Teacher;
import io.github.dndanoff.school.domain.ports.out.TeacherRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class TeacherRepositoryImpl extends SpecificationAwareRepository<Student> implements TeacherRepository {
	
	@PersistenceContext
    private final EntityManager em;
	private final TeacherBasicRepository jpaRepo;
	
	@Autowired
	public TeacherRepositoryImpl(EntityManager em, TeacherBasicRepository jpaRepo) {
		this.em = em;
		this.jpaRepo = jpaRepo;
	}

	@Override
	public Teacher save(Teacher entity) {
		return jpaRepo.save(entity);
	}

	@Override
	public Optional<Teacher> findById(Long id) {
		return jpaRepo.findById(id);
	}

	@Override
	public Iterable<Teacher> findAll() {
		return jpaRepo.findAll();
	}

	@Override
	public long count() {
		return jpaRepo.count();
	}

	@Override
	public void deleteById(Long id) {
		jpaRepo.deleteById(id);
	}
	
	@Repository
	interface TeacherBasicRepository extends JpaRepository<Teacher, Long>{
		
	}
}
