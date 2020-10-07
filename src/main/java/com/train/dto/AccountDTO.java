package com.train.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDTO
{
	private Long id;

	private String name;

	private String phone;

	private String email;

	private String password;
}
