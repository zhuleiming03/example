package com.example.jpa.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dbo.Course")
@Data
public class CoursePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private Integer courseID;

    @Column(name = "CourseName")
    private String name;

    @Column(name = "CourserCode")
    private String code;
}
