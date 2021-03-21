package io.github.dndanoff.school.domain.ports.out;

import java.util.List;
import java.util.Optional;

import io.github.dndanoff.school.domain.model.OutboxMessage;

public interface OutboxMessageRepository {
	public OutboxMessage save(OutboxMessage message);
	  
    public List<OutboxMessage> saveAll(List<OutboxMessage> messages);
    
    public Optional<OutboxMessage> findById(Long id);
    
    public List<OutboxMessage> findAll();

    public List<OutboxMessage> findAllByProcessedOrderByCreatedAtAsc(Boolean processed);
}
