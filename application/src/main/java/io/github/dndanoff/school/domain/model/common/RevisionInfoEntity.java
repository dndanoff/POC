package io.github.dndanoff.school.domain.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@RevisionEntity
@Table(name = "revinfo")
@Getter
@Setter
public class RevisionInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "REV")
    @RevisionNumber
    private Long rev;
 
    @Column(name = "revtstmp")
    @RevisionTimestamp
    private long timestamp;
}