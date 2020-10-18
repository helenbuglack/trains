package com.train.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TicketDTO
{
	private Long id;

	private int numberPerson;

	private String seats;

	private String fromPoint;

	private String toPoint;

	private String time;

	private Long accountId;

	private Long trainId;

	private String name;

	private String surname;

	private String passport;
}
