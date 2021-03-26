package io.github.dndanoff.school.application.adapters.out.repo;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.school.domain.model.Student;
import io.github.dndanoff.school.domain.ports.out.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class StudentRepositoryImpl extends SpecificationAwareRepository<Student> implements StudentRepository {
	@PersistenceContext
    private final EntityManager em;
	private final StudentBasicRepository jpaRepo;
	
	@Autowired
	public StudentRepositoryImpl(EntityManager em, StudentBasicRepository jpaRepo) {
		this.em = em;
		this.jpaRepo = jpaRepo;
	}

	@Override
	public Student save(Student entity) {
		return jpaRepo.save(entity);
	}

	@Override
	public Optional<Student> findById(Long id) {
		return jpaRepo.findById(id);
	}

	@Override
	public Iterable<Student> findAll() {
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
	interface StudentBasicRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{
		
	}
}
