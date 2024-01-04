package com.charter.entity.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long id;

    private Integer amount;

    private Long customerId;

    private LocalDate createTime;
}
