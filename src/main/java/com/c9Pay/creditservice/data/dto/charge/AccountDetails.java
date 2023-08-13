package com.c9Pay.creditservice.data.dto.charge;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    @Min(0)
    Long credit;

    @NotBlank
    String serialNumber;
}
