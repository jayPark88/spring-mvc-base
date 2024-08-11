package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends BaseInfoEntity{

    @Id
    @Column(name = "category_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySeq;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
