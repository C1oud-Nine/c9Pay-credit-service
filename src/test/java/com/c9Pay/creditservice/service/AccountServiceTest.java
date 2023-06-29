package com.c9Pay.creditservice.service;

import com.c9Pay.creditservice.entity.Account;
import com.c9Pay.creditservice.repository.AccountRepository;
import com.c9Pay.creditservice.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void createNewAccount_ValidSerialNumber_Success() {
        String serialNumber = "123456";
        when(accountRepository.findAccountBySerialNumber(serialNumber))
                .thenReturn(Collections.emptyList());

        accountService.createNewAccount(serialNumber);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void createNewAccount_DuplicateSerialNumber_ThrowsIllegalStateException() {
        String serialNumber = "123456";
        List<Account> existingAccounts = List.of(new Account());
        when(accountRepository.findAccountBySerialNumber(serialNumber))
                .thenReturn(existingAccounts);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            accountService.createNewAccount(serialNumber);
        });

        verify(accountRepository, never()).save(any(Account.class));
    }
}
