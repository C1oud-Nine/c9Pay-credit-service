package com.c9Pay.creditservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ChargeForm {
    private Integer chargeAmount;
    private String accountInfo;
}
