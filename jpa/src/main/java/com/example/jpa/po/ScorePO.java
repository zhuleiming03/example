package com.example.jpa.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dbo.Score")
@SecondaryTables({@SecondaryTable(name = "Student"),@SecondaryTable(name = "Course")})
public class ScorePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreID")
    private Integer scoreID;

    @Column(name = "StudentID")
    private Integer studentID;

    @Column(name = "CourseID")
    private Integer courseID;

    @Column(name = "Score")
    private Float score;

    @Column(table = "Student", name = "StudentName")
    private String student;

    @Column(table = "Course", name = "CourseName")
    private String course;

    @Column(table = "Course", name = "CourserCode")
    private String courseKey;

}
