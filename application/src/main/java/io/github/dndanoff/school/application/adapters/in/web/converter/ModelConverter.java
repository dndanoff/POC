package io.github.dndanoff.school.application.adapters.in.web.converter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.dndanoff.school.application.adapters.in.web.dto.common.Dto;
import io.github.dndanoff.school.domain.model.common.BaseEntity;

public interface ModelConverter<E extends BaseEntity, D extends Dto> {

	E dtoToEntity(D dto);
	
	D entityToDto(E entity);

	default List<E> dtoToEntity(Collection<D> dtos) {
		return Optional.ofNullable(dtos).map(Collection::stream).orElseGet(Stream::empty)
				.map(this::dtoToEntity).collect(Collectors.toList());
	}

	default List<D> entityToDto(Collection<E> entities) {
		return Optional.ofNullable(entities).map(Collection::stream).orElseGet(Stream::empty)
				.map(this::entityToDto).collect(Collectors.toList());
	}
}
