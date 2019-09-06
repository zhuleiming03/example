package com.example.jpa.po;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "dbo.Student")
@Data
public class StudentPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID")
    private Integer studentID;

    @Column(name = "StudentName")
    private String studentName;

    @Column(name = "Birthday")
    private LocalDateTime birthday;
}
