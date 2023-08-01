package com.c9Pay.creditservice.web.dto.charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeForm {
    private Long chargeAmount;
    private String serialNumber;
}
