package io.github.dndanoff.school.domain.model.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingCriteria {
	private final Integer size;
	private final Integer page;
}
