package io.github.dndanoff.school.domain.ports.out;

import io.github.dndanoff.school.domain.model.OutboxMessage;

public interface OutboxMessageSender {
	public void send(OutboxMessage message);
}
