package io.github.dndanoff.school.domain.model.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class, BaseEntityListener.class})
public abstract class BaseEntity {
    @Id
	@Setter(AccessLevel.PRIVATE)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
    
    @Column(nullable = false)
    protected Boolean active;

    @Column(nullable = false)
    @Version
    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    protected LocalDateTime lastModified;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    protected LocalDateTime createdAt;

	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
