package com.example.jpa.po;

import lombok.Data;

import javax.persistence.*;

@Data
//@Entity
//@Table(name = "dbo.ClassRoom")
public class ClassRoomPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassRoomID")
    private Integer code;

    @Column(name = "Capacity")
    private Integer capacity;

    @Embedded
    private BlackboardPO blackboardPO;

}
