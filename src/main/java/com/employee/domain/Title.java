package com.employee.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@Where(clause = "deleted = 0")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
}
