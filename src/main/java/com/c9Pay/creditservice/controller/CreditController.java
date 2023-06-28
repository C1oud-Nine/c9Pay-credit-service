package com.c9Pay.creditservice.controller;

import com.c9Pay.creditservice.dto.charge.AccountDetails;
import com.c9Pay.creditservice.dto.charge.ChargeAmount;
import com.c9Pay.creditservice.dto.charge.ChargeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
public class CreditController {
   @PostMapping("/{identifier}")
    public ResponseEntity<?> createAccount(@PathVariable String identifier){
       log.info("Id : {}", identifier);
       return ResponseEntity.ok().build();
   }

   @GetMapping("/{identifier}")
    public ResponseEntity<AccountDetails> getAccount(@PathVariable String identifier){
       AccountDetails accountDetails = new AccountDetails("test", 1234L);
       return ResponseEntity.ok(accountDetails);
   }


   @PostMapping("/{identifier}/load")
    public ResponseEntity<?> loadCredit(@PathVariable String identifier, @RequestBody ChargeForm form){
       return ResponseEntity.ok().build();
   }

   @PostMapping("/{to}/transfer/{from}")
    public ResponseEntity<?> transfer(@PathVariable(name= "to") String to, @PathVariable(name="from") String from,
                                      @RequestBody ChargeAmount chargeAmount){
       return ResponseEntity.ok().build();
   }

}
