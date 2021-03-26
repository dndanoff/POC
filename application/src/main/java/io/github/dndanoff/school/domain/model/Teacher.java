package io.github.dndanoff.school.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import io.github.dndanoff.school.domain.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TEACHER")
@SQLDelete(sql = "UPDATE teacher SET active=false WHERE id=?")
@Getter
@Setter
@Audited
public class Teacher extends BaseEntity{
    
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
}
