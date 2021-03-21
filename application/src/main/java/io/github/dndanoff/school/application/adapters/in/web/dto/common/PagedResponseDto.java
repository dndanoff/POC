package io.github.dndanoff.school.application.adapters.in.web.dto.common;

import java.util.List;

import lombok.Data;

@Data
public class PagedResponseDto <D extends Dto> {
	private PageDto page;
	private SortDto sorting;
	private Long totalResults;
	private Long totalPages;
	private List<D> results;
}
