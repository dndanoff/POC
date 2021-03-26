package io.github.dndanoff.school.application.adapters.out.repo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import io.github.dndanoff.school.domain.model.vo.common.Filter;
import io.github.dndanoff.school.domain.model.vo.common.FilterOperator;
import io.github.dndanoff.school.domain.model.vo.common.PagingCriteria;
import io.github.dndanoff.school.domain.model.vo.common.SearchCriteria;
import io.github.dndanoff.school.domain.model.vo.common.SortCriteria;

public class SpecificationAwareRepository<E> {
	
	Pageable toPageable(PagingCriteria paging) {
		if(paging == null || paging.getSize() == null || paging.getPage() == null || paging.getSize() < 1 || paging.getPage() < 0) {
			Pageable.unpaged();
		}
		return PageRequest.of(paging.getPage(), paging.getSize());
	}
	
	Sort toSort(SortCriteria sortCriteria) {
		if(sortCriteria == null || !StringUtils.hasText(sortCriteria.getField())) {
			return Sort.unsorted();
		}
		
		if(!StringUtils.hasText(sortCriteria.getDirection()) || "ASC".equals(sortCriteria.getDirection())) {
			return Sort.by(sortCriteria.getField());
		}
		
		return Sort.by(sortCriteria.getField()).descending();
	}
	
	Specification<E> toSpecification(SearchCriteria searchCriteria){
		if(searchCriteria == null || searchCriteria.getFilters().isEmpty()) {
			return null;
		}
		List<Filter> filters = searchCriteria.getFilters();
		Specification<E> specification = Specification.where(toSpecification(filters.remove(0)));
        for (Filter filter : filters) {
        	specification = specification.and(toSpecification(filter));
        }
        return specification;
	}
	
	Specification<E> toSpecification(Filter filter){
		if(filter == null) {
			return (root, query, criteriaBuilder) ->            
            			criteriaBuilder.conjunction();
		}
		
		if(FilterOperator.EQUALS == filter.getOperator()) {
			return (root, query, criteriaBuilder) ->
		            criteriaBuilder.equal(root.get(filter.getField()),
		            castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
		} else if(FilterOperator.GREATER_THAN == filter.getOperator()) {
			return (root, query, criteriaBuilder) ->
		            criteriaBuilder.gt(root.get(filter.getField()),
		            (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
		} else if(FilterOperator.LESS_THAN == filter.getOperator()) {
			return (root, query, criteriaBuilder) ->
		            criteriaBuilder.lt(root.get(filter.getField()),
		            (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
		}
		
		return (root, query, criteriaBuilder) ->            
        			criteriaBuilder.conjunction();
	}
	
	private Object castToRequiredType(Class<?> fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)){
            return Double.valueOf(value);
        }else if(fieldType.isAssignableFrom(Integer.class)){
            return Integer.valueOf(value);
        }else if(fieldType.isAssignableFrom(String.class)){
            return value;
        }
        return null;
    }
}
