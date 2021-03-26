package io.github.dndanoff.school.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import io.github.dndanoff.school.domain.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="STUDENT")
@SQLDelete(sql = "UPDATE student SET active=false WHERE id=?")
@Getter
@Setter
@Audited
public class Student extends BaseEntity{
    
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(name = "CLASS")
    private String schoolClass;
    @Column
    private Integer grade;
    @Column
    private String foreignLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOREIGN_LANGUAGE_TEACHER_ID")
    private Teacher teacher;
}
