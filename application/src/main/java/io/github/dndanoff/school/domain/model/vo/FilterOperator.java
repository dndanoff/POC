package io.github.dndanoff.school.domain.model.vo;

import java.util.Optional;

import lombok.Getter;

@Getter
public enum FilterOperator {
	GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS(":");
	
	private String symbol;
	
	private FilterOperator(String symbol) {
		this.symbol = symbol;
	}
	
	public static Optional<FilterOperator> findOperatorBySymbol(String symbol) {
		FilterOperator operator = null;
		
		for(FilterOperator op : values()) {
			if(op.getSymbol().equals(symbol)) {
				operator = op;
				break;
			}
		}
		
		return Optional.ofNullable(operator);
	}
}
