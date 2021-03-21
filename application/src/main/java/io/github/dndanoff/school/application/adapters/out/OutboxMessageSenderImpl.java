package io.github.dndanoff.school.application.adapters.out;

import org.springframework.stereotype.Component;

import io.github.dndanoff.school.domain.model.OutboxMessage;
import io.github.dndanoff.school.domain.ports.out.OutboxMessageSender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OutboxMessageSenderImpl implements OutboxMessageSender{

	@Override
	public void send(OutboxMessage message) {
		log.info("Sending message with payload={} to topic={}", message.getPayload(), message.getTopic());
	}

}
