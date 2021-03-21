package io.github.dndanoff.school.domain.model.vo.error;

public class IllegalOperationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalOperationException(String operation) {
		super("Illegal search operation:" + operation);
	}
}
