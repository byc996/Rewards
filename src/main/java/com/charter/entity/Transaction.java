package com.charter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_time")
    private LocalDate createTime;


}
