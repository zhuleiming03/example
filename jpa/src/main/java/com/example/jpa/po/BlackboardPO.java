package com.example.jpa.po;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
//@Entity
//@Table(name = "dbo.Blackboard")
public class BlackboardPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlackboardID")
    private Integer code;

    @Column(name = "Length")
    private Integer length;

    @Column(name = "Width")
    private Integer width;
}
