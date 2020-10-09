package com.train.service.impl;

import com.train.dto.AccountDTO;
import com.train.entity.AccountEntity;
import com.train.entity.RoleEntity;
import com.train.enums.Roles;
import com.train.repository.AccountRepository;
import com.train.repository.RoleRepository;
import com.train.service.AccountService;
import com.train.service.converter.Converter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;


@Service
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService
{
	private final AccountRepository accountRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final RoleRepository roleRepository;
	private final Converter<AccountEntity, AccountDTO> converter;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,
			Converter<AccountEntity, AccountDTO> converter)
	{
		this.accountRepository = accountRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
		this.converter = converter;
	}

	@Override
	public ResponseEntity<String> createAccount(final AccountDTO accountDTO)
	{
		if (getAccountsByParams(accountDTO.getPhone(), accountDTO.getEmail()).isEmpty())
		{
			final AccountEntity entity = converter.convertToEntity(accountDTO);
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			setRoles(entity, Roles.ROLE_USER);
			accountRepository.save(entity);

			return new ResponseEntity<>(HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<AccountEntity> getAccountsByParams(final String phone, final String email)
	{
		return accountRepository.findAccountByEmailOrPhone(email, phone);
	}

	@Override
	public AccountEntity getCurrentAccount()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		return accountRepository.findByEmail(email);
	}



	private void setRoles(final AccountEntity entity, final Roles role)
	{

		Set<RoleEntity> roles = new HashSet<>();
		roles.add(roleRepository.findByRole(role));

		entity.setRoleSet(roles);
	}
}
