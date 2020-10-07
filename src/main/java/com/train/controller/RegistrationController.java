package com.train.controller;

import com.train.dto.AccountDTO;
import com.train.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/account")
public class RegistrationController
{
	private final AccountService accountService;

	@Autowired
	public RegistrationController(AccountService accountService)
	{
		this.accountService = accountService;
	}

	@PostMapping(value = "/registration")
	public ResponseEntity registration(@RequestBody AccountDTO accountDto)
	{
		return accountService.createAccount(accountDto);
	}

}
