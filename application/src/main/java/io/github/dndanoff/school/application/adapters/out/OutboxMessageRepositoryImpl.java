package io.github.dndanoff.school.application.adapters.out;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.school.domain.model.OutboxMessage;
import io.github.dndanoff.school.domain.model.Student;
import io.github.dndanoff.school.domain.ports.out.OutboxMessageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class OutboxMessageRepositoryImpl extends SpecificationAwareRepository<Student> implements OutboxMessageRepository {
	
	@PersistenceContext
    private final EntityManager em;
	private final OutboxMessageBasicRepository jpaRepo;
	
	@Autowired
	public OutboxMessageRepositoryImpl(EntityManager em, OutboxMessageBasicRepository jpaRepo) {
		this.em = em;
		this.jpaRepo = jpaRepo;
	}

	@Override
	public OutboxMessage save(OutboxMessage message) {
		return jpaRepo.save(message);
	}

	@Override
	public List<OutboxMessage> saveAll(List<OutboxMessage> messages) {
		return jpaRepo.saveAll(messages);
	}

	@Override
	public Optional<OutboxMessage> findById(Long id) {
		return jpaRepo.findById(id);
	}

	@Override
	public List<OutboxMessage> findAll() {
		return jpaRepo.findAll();
	}

	@Override
	public List<OutboxMessage> findAllByProcessedOrderByCreatedAtAsc(Boolean processed) {
		return jpaRepo.findAllByProcessedOrderByCreatedAtAsc(processed);
	}
	
	@Repository
	interface OutboxMessageBasicRepository extends JpaRepository<OutboxMessage, Long>{
		public List<OutboxMessage> findAllByProcessedOrderByCreatedAtAsc(Boolean processed);
	}
}
