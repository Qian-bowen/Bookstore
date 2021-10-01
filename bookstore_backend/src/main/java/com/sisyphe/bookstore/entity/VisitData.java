package com.sisyphe.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_data")
@Data
public class VisitData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "visit_num")
    private int visitNum;

    @Column(name="record_time")
    private LocalDateTime recordTime;

    public VisitData(){}

    public VisitData(int visitNum,LocalDateTime recordTime)
    {
        this.recordTime=recordTime;
        this.visitNum=visitNum;
    }
}
