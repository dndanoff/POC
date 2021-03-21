package io.github.dndanoff.school.domain.model.common;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class BaseEntityListener {
	@PrePersist
    @PreUpdate
    private void beforeAnyUpdate(BaseEntity entity) {
		if(entity.getActive() == null) {
			entity.setActive(true);
    	}
    }
	
    @PreRemove
    public void deactivate(BaseEntity entity) {
    	if(entity.getActive() == null || entity.getActive()) {
    		entity.setActive(false);
    	}
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(BaseEntity entity) {
        
    }
}
