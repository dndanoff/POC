package io.github.dndanoff.school.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import io.github.dndanoff.school.domain.model.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="OUTBOX_MESSAGE")
@SQLDelete(sql = "UPDATE OUTBOX_MESSAGE SET active=false WHERE id=?")
@Getter
@Setter
@NoArgsConstructor
public class OutboxMessage extends BaseEntity{
	@Lob
    @Type(type = "org.hibernate.type.TextType")
    private String payload;
	@Column
    private String topic;
    @Column
    private LocalDateTime processedAt;
    @Column
    private String operation;
    @Column
    private String className;
    @Column(nullable = false)
    private Boolean processed;
    
    @PrePersist
    @PreUpdate
    public void setDefaults() {
    	if(this.processed == null) {
    		this.processed = false;
    	}
    }
}
