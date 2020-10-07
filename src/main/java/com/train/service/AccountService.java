package com.train.service;

import com.train.dto.AccountDTO;
import com.train.entity.AccountEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AccountService
{
	ResponseEntity<String> createAccount(AccountDTO accountDTO);

	List<AccountEntity> getAccountsByParams(String phone, String email);
}
