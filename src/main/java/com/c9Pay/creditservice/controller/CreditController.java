package com.c9Pay.creditservice.controller;

import com.c9Pay.creditservice.dto.ChargeAmount;
import com.c9Pay.creditservice.dto.ChargeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/credit-service")
public class Controller {
   @PostMapping("/account/{identifier}")
    public ResponseEntity<?> createAccount(@PathVariable String identifier){
       log.info("Id : {}", identifier);
       return ResponseEntity.ok().build();
   }

   @GetMapping("/account/{identifier}")
    public ResponseEntity<?> getAccount(@PathVariable String identifier){
       return ResponseEntity.ok().build();
   }


   @PostMapping("/account/{identifier}/load")
    public ResponseEntity<?> loadCredit(@PathVariable String identifier, @RequestBody ChargeForm form){
       return ResponseEntity.ok().build();
   }

   @PostMapping("/account/{to}/transfer/{from}")
    public ResponseEntity<?> transfer(@PathVariable(name= "to") String to, @PathVariable(name="from") String from,
                                      @RequestBody ChargeAmount chargeAmount){
       log.info("to:{} , from: {}" , to , from);
       log.info("amount value: {}", chargeAmount.getCreditAmount());
       return ResponseEntity.ok().build();
   }

}
