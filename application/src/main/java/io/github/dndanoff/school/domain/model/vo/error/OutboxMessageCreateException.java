package io.github.dndanoff.school.domain.model.vo.error;

public class OutboxMessageCreateException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OutboxMessageCreateException(Throwable t) {
		super(t);
	}
}
