package io.github.dndanoff.school.domain.model.vo;

import io.github.dndanoff.school.domain.model.vo.error.IllegalOperationException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Filter{
	private final String field;
	private final FilterOperator operator;
	private final String value;
	
	public Filter(String field, String operation, String value) throws IllegalOperationException{
		this.field = field;
		this.value = value;
		this.operator = FilterOperator.findOperatorBySymbol(operation).orElseThrow(() -> new IllegalOperationException("operation"));
	}
}
