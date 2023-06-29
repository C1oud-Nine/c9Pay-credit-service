package com.c9Pay.creditservice.service;

import com.c9Pay.creditservice.entity.Account;
import com.c9Pay.creditservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    @Transactional
    public void createNewAccount(String serialNumber){
        Account newAccount = Account.builder()
                .creditAmount(0L)
                .serialNumber(serialNumber)
                .build();
        checkValidation(newAccount);
        accountRepository.save(newAccount);
    }

    private void checkValidation(Account account){
        List<Account> accounts = accountRepository.findAccountBySerialNumber(account.getSerialNumber());
        if(!accounts.isEmpty()) throw new IllegalStateException("이미 계좌가 생성 되었습니다.");

    }
}
