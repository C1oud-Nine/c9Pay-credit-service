package com.c9Pay.creditservice.dto.charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {
    //@TODO 필요 추가 정보 추후 추가
    String username;
    Long credit;
}
