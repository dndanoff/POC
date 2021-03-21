package io.github.dndanoff.school.application.adapters.in.schdulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.dndanoff.school.domain.ports.in.UnprocessedMessageHandler;

@Component
public class OutboxMessageFacade {

	private final UnprocessedMessageHandler unprocessedMessageHandler;
	
	@Autowired
	public OutboxMessageFacade(UnprocessedMessageHandler unprocessedMessageHandler) {
		this.unprocessedMessageHandler = unprocessedMessageHandler;
	}
	
	public void handleUnprocessedMessages() {
		unprocessedMessageHandler.handleUnprocessedMessages();
	}

}
