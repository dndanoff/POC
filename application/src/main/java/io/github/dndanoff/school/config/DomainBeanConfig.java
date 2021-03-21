package io.github.dndanoff.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.dndanoff.school.domain.ports.out.OutboxMessageRepository;
import io.github.dndanoff.school.domain.ports.out.OutboxMessageSender;
import io.github.dndanoff.school.domain.ports.out.StudentRepository;
import io.github.dndanoff.school.domain.ports.out.TeacherRepository;
import io.github.dndanoff.school.domain.service.OutboxMessageService;
import io.github.dndanoff.school.domain.service.StudentService;
import io.github.dndanoff.school.domain.service.TeacherService;

@Configuration
public class DomainBeanConfig {

	@Bean
	public OutboxMessageService outboxMessageService(OutboxMessageRepository outboxRepo, OutboxMessageSender outboxMessageSender) {
		return new OutboxMessageService(outboxRepo, outboxMessageSender);
	}
	
	@Bean
	public StudentService studentService(StudentRepository studentRepo) {
		return new StudentService(studentRepo);
	}
	
	@Bean
	public TeacherService teacherService(TeacherRepository teacherRepo) {
		return new TeacherService(teacherRepo);
	}
	
}
