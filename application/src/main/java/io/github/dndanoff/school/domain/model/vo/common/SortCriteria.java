package io.github.dndanoff.school.domain.model.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortCriteria {
	private final String field;
	private final String direction;
}
