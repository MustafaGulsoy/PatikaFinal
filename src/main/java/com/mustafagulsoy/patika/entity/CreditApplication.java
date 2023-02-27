package com.mustafagulsoy.patika.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor

public class CreditApplication {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date applicationDate;
    boolean status;
    float assignedLimit;
    @OneToOne
    User applicant;
}
