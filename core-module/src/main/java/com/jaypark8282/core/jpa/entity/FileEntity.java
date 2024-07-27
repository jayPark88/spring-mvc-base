package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "file")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileEntity extends BaseInfoEntity{
    @Id
    @Column(name = "file_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileSeq;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "category_seq")
    private Long categorySeq;
}
