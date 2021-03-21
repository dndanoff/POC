package io.github.dndanoff.school.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;

import io.github.dndanoff.school.domain.model.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert
@Table(name="TEACHER")
@SQLDelete(sql = "UPDATE teacher SET active=false WHERE id=?")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends BaseEntity{
    
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    
    @Override
    public int hashCode() {
        return 13;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
            Teacher other = (Teacher) obj;
        return getId() != null && getId().equals(other.getId());
    }
}
