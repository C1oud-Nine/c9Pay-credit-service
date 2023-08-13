package com.c9Pay.creditservice.data.entity;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private Long creditAmount;


    public void incrementCredit(Long credit){
        this.creditAmount += credit;
    }
    public void decrementCredit(Long credit){
        if(this.creditAmount < credit) throw new IllegalStateException();
        else this.creditAmount -= credit;
    }
}
