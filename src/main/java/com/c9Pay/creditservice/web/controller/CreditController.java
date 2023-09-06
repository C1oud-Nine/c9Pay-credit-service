package com.c9Pay.creditservice.web.controller;

import com.c9Pay.creditservice.data.dto.charge.AccountDetails;
import com.c9Pay.creditservice.data.dto.charge.ChargeAmount;
import com.c9Pay.creditservice.data.dto.charge.ChargeForm;
import com.c9Pay.creditservice.data.dto.log.ChargeLogDto;
import com.c9Pay.creditservice.data.entity.Account;
import com.c9Pay.creditservice.web.service.AccountService;
import com.c9Pay.creditservice.web.service.LogService;
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

    private final LogService logService;
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
       details.setSerialNumber(findAccount.getSerialNumber());
       return ResponseEntity.ok(details);
   }


   @PostMapping("/{identifier}/load")
    public ResponseEntity<?> loadCredit(@PathVariable String identifier, @RequestBody ChargeForm form, HttpServletRequest request){
       accountService.loadCredit(identifier, form.getQuantity());
       logService.createChargeLog(new ChargeLogDto(identifier,form.getQuantity()));
       log.info("충전 금액: {}", form.getQuantity());
       return ResponseEntity.ok().build();
   }

   @PostMapping("/{from}/transfer/{to}")
    public ResponseEntity<?> transfer(@PathVariable(name= "to") String to, @PathVariable(name="from") String from,
                                      @RequestBody ChargeAmount chargeAmount){
       accountService.transfer(from, to, chargeAmount.getCreditAmount());
       logService.transferLog(new ChargeLogDto(from, to, chargeAmount.getCreditAmount()));
       return ResponseEntity.ok("transfer successful");
   }

}
