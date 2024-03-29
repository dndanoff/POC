package io.github.dndanoff.school.application.adapters.in.web.dto.error;

public class FieldErrorDto {
	
	private final String field;
	private final String message;

	public FieldErrorDto(final String field, final String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
