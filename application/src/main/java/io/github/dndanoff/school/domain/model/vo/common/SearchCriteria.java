package io.github.dndanoff.school.domain.model.vo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.dndanoff.school.domain.model.vo.error.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchCriteria {

	private List<Filter> filters = new ArrayList<>();
	
	public SearchCriteria(String search) {
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?);");
        Matcher matcher = pattern.matcher(search + ";");
        while (matcher.find()) {
            try {
            	add(new Filter(matcher.group(1), matcher.group(2), matcher.group(3)));
			} catch (IllegalOperationException e) {
				log.error(e.getMessage(), e);
			}
        }
	}

	public SearchCriteria add(final Filter filter) {
		filters.add(filter);
		return this;
	}
	
	public void setFilters(List<Filter> filters) {
		if(filters != null) {
			this.filters = new ArrayList<>(filters);
		}
	}
	
	public List<Filter> getFilters(){
		return new ArrayList<>(filters);
	}
	
	
}
