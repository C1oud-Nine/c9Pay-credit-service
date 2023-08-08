package com.c9Pay.creditservice.web.dto.charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeForm {
    private Long quantity;


}