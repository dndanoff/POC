package io.github.dndanoff.school.application.adapters.in.schedulers;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Slf4j
@Component
class OutboxScheduler {
	
	private final OutboxMessageFacade outboxMessageFacade;
	
	@Autowired
	OutboxScheduler(OutboxMessageFacade outboxMessageFacade) {
		this.outboxMessageFacade = outboxMessageFacade;
	}

	@Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "checkForUnprocessedMessagesTask", 
      lockAtLeastForString = "PT10S", lockAtMostForString = "PT30S")
    void checkForUnprocessedMessages() {
		MDC.clear();
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString());
		log.info("Triggering checkForUnprocessedMessages() iteration.");
		outboxMessageFacade.handleUnprocessedMessages();
		MDC.clear();
    }
}
