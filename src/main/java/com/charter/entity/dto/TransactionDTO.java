package com.charter.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Integer amount;

    private Long customerId;

    private Date createTime;
}
