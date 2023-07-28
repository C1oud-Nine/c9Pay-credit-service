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
       log.info("Starting registration for a new credit account");
       try{
           accountService.createNewAccount(serialNumber);
           return ResponseEntity.ok().build();

       }
       catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
   }

   @DeleteMapping("/{serialNumber}")
   public ResponseEntity<?> deleteAccount(@PathVariable String serialNumber){
       accountService.deleteAccount(serialNumber);
       return ResponseEntity.ok("계좌 삭제 성공");
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
       try {
           accountService.loadCredit(identifier, form.getChargeAmount());
           return ResponseEntity.ok("입금 성공");
       }
       catch (Exception ignored){
           return ResponseEntity.badRequest().build();
       }

   }

   @PostMapping("/{from}/transfer/{to}")
    public ResponseEntity<?> transfer(@PathVariable(name= "to") String to, @PathVariable(name="from") String from,
                                      @RequestBody ChargeAmount chargeAmount){
       try{
           accountService.transfer(from, to, chargeAmount.getCreditAmount());
           return ResponseEntity.ok("송금 성공");
       }catch (Exception ignored){
           return ResponseEntity.badRequest().build();
       }

   }

}
