package com.c9Pay.creditservice.service;

import com.c9Pay.creditservice.entity.Account;
import com.c9Pay.creditservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        if(isExist(newAccount))
            throw new IllegalStateException("이미 생성된 계좌입니다.");
        accountRepository.save(newAccount);
    }

    public Account getAccountInfo(String serialNumber){
        return accountRepository.findAccountBySerialNumber(serialNumber).orElseThrow();
    }

    public void deleteAccount(String serialNumber){
        Account account = accountRepository.findAccountBySerialNumber(serialNumber)
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);
    }
    private boolean isExist(Account account){
        Optional<Account> accounts = accountRepository.findAccountBySerialNumber(account.getSerialNumber());
        return accounts.isPresent();
    }
}
