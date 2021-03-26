package io.github.dndanoff.school.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.dndanoff.school.domain.model.OutboxMessage;
import io.github.dndanoff.school.domain.ports.in.UnprocessedMessageHandler;
import io.github.dndanoff.school.domain.ports.out.OutboxMessageSender;
import io.github.dndanoff.school.domain.ports.out.repo.OutboxMessageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutboxMessageService implements UnprocessedMessageHandler{

	private final OutboxMessageRepository outboxRepo;
	private final OutboxMessageSender outboxMessageSender;

	public OutboxMessageService(OutboxMessageRepository outboxRepo, OutboxMessageSender outboxMessageSender) {
		this.outboxRepo = outboxRepo;
		this.outboxMessageSender = outboxMessageSender;
	}
	
	public OutboxMessage save(OutboxMessage message) {
		log.info("Received request to save()");
		log.debug("with params: message={}", message);
		return outboxRepo.save(message);
	}

	public void handleUnprocessedMessages() {
		log.info("Received request to handleUnprocessedMessages()");
		List<OutboxMessage> unprocessedMessages = new ArrayList<>();
		outboxRepo.findAllByProcessedOrderByCreatedAtAsc(false).forEach(m -> unprocessedMessages.add(m));
		log.debug("Found the following unprocessed messages={}", unprocessedMessages.stream().map(m -> m.getId()).collect(Collectors.toList()));
		unprocessedMessages.forEach(m -> {
										outboxMessageSender.send(m);
										m.setProcessed(true);
										m.setProcessedAt(LocalDateTime.now());
									});
		outboxRepo.saveAll(unprocessedMessages);
	}
}
