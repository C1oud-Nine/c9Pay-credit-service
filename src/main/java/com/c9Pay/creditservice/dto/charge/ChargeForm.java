package com.c9Pay.creditservice.dto.charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeForm {
    private Integer chargeAmount;
    private String accountInfo;
}
