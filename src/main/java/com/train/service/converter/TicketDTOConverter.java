package com.train.service.converter;

import com.train.dto.TicketDTO;
import com.train.entity.TicketEntity;
import com.train.entity.TrainEntity;
import com.train.service.AccountService;
import com.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TicketDTOConverter implements Converter<TicketEntity, TicketDTO>
{
	private final AccountService accountService;
	private final TrainService trainService;

	@Autowired
	public TicketDTOConverter(AccountService accountService, TrainService trainService)
	{
		this.accountService = accountService;
		this.trainService = trainService;
	}

	@Override
	public TicketDTO convertToDTO(TicketEntity ticketEntity)
	{
		return null;
	}

	@Override
	public TicketEntity convertToEntity(TicketDTO dto)
	{
		TicketEntity entity = new TicketEntity();

		entity.setNumberPerson(dto.getNumberPerson());
		entity.setSeats(dto.getSeats());
		entity.setAccount(accountService.getCurrentAccount());
		entity.setTrain(getTrain(dto.getTrainId()));
		entity.setFromPoint(entity.getTrain().getFromPoint());
		entity.setToPoint(entity.getTrain().getToPoint());
		entity.setTime(entity.getTrain().getExitTime());
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setPassport(dto.getPassport());

		return entity;
	}

	private TrainEntity getTrain(Long trainId)
	{
		return trainService.getOne(trainId).orElseThrow(IllegalArgumentException::new);
	}


}


