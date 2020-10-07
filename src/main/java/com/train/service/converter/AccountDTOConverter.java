package com.train.service.converter;

import com.train.dto.AccountDTO;
import com.train.entity.AccountEntity;
import org.springframework.stereotype.Component;


@Component
public class AccountDTOConverter implements Converter<AccountEntity , AccountDTO>
{
	@Override
	public AccountDTO convertToDTO(final AccountEntity entity)
	{
		AccountDTO dto = new AccountDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setPassword(entity.getPassword());

		return dto;
	}

	@Override
	public AccountEntity convertToEntity(final AccountDTO dto)
	{
		AccountEntity entity = new AccountEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setPassword(dto.getPassword());

		return entity;
	}

}
