package com.c9Pay.creditservice.web.controller;

import com.c9Pay.creditservice.web.dto.charge.AccountDetails;
import com.c9Pay.creditservice.web.dto.charge.ChargeAmount;
import com.c9Pay.creditservice.web.dto.charge.ChargeForm;
import com.c9Pay.creditservice.entity.Account;
import com.c9Pay.creditservice.web.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
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
       accountService.createNewAccount(serialNumber);
       return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{serialNumber}")
   public ResponseEntity<?> deleteAccount(@PathVariable String serialNumber){
       accountService.deleteAccount(serialNumber);
       return ResponseEntity.ok().build();
   }
   @GetMapping("/{serialNumber}")
    public ResponseEntity<AccountDetails> getAccount(@PathVariable String serialNumber){
       Account findAccount= accountService.getAccountInfo(serialNumber);
       AccountDetails details = new AccountDetails();
       details.setCredit(findAccount.getCreditAmount());
       return ResponseEntity.ok(details);
   }


   @PostMapping("/{identifier}/load")
    public ResponseEntity<?> loadCredit(@PathVariable String identifier, @RequestBody ChargeForm form, HttpServletRequest request){
       accountService.loadCredit(identifier, form.getQuantity());
       log.info("충전 금액: {}", form.getQuantity());
       return ResponseEntity.ok().build();
   }

   @PostMapping("/{from}/transfer/{to}")
    public ResponseEntity<?> transfer(@PathVariable(name= "to") String to, @PathVariable(name="from") String from,
                                      @RequestBody ChargeAmount chargeAmount){
       accountService.transfer(from, to, chargeAmount.getCreditAmount());
       return ResponseEntity.ok("transfer successful");
   }

}
