package com.c9Pay.creditservice.controller;

import com.c9Pay.creditservice.dto.charge.AccountDetails;
import com.c9Pay.creditservice.dto.charge.ChargeAmount;
import com.c9Pay.creditservice.dto.charge.ChargeForm;
import com.c9Pay.creditservice.entity.Account;
import com.c9Pay.creditservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class CreditController {

    private final AccountService accountService;

   @PostMapping("/{serialNumber}")
    public ResponseEntity<?> createAccount(@PathVariable String serialNumber){
       try{
           accountService.createNewAccount(serialNumber);
           return ResponseEntity.ok().build();

       }
       catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
   }

   @GetMapping("/{serialNumber}")
    public ResponseEntity<AccountDetails> getAccount(@PathVariable String serialNumber){
       try{
           Account findAccount= accountService.getAccountInfo(serialNumber);
           AccountDetails details = new AccountDetails();
           details.setCredit(findAccount.getCreditAmount());
           return ResponseEntity.ok(details);
       }
       catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
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
