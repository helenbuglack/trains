package com.train.service.converter;

import com.train.dto.TrainDTO;
import com.train.entity.TrainEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class TrainDTOConverter implements Converter<TrainEntity, TrainDTO>
{
	@Override
	public TrainDTO convertToDTO(final TrainEntity entity)
	{
		TrainDTO dto = new TrainDTO();
		dto.setId(entity.getId());
		dto.setFromPoint(entity.getFromPoint());
		dto.setToPoint(entity.getToPoint());
		dto.setExitDate(entity.getExitDate());
		dto.setArrivalDate(entity.getArrivalDate());
		dto.setExitTime(entity.getExitTime());
		dto.setArrivalTime(entity.getArrivalTime());
		dto.setType(entity.getType());
		dto.setCost(entity.getCost());
		dto.setSeatingCapacity(entity.getSeatingCapacity());
		dto.setFreeSeats(entity.getFreeSeats());
		dto.setRoutePoints(entity.getRoutePoints());
		dto.setDuration(entity.getDuration());

		return dto;
	}

	@SneakyThrows
	@Override
	public TrainEntity convertToEntity(final TrainDTO dto)
	{
		TrainEntity entity = new TrainEntity();
		entity.setId(dto.getId());
		entity.setFromPoint(dto.getFromPoint());
		entity.setToPoint(dto.getToPoint());
		entity.setExitDate(dto.getExitDate());
		entity.setArrivalDate(dto.getArrivalDate());
		entity.setExitTime(dto.getExitTime());
		entity.setArrivalTime(dto.getArrivalTime());
		entity.setType(dto.getType());
		entity.setCost(dto.getCost());
		entity.setSeatingCapacity(dto.getSeatingCapacity());
		entity.setFreeSeats(dto.getFreeSeats());
		entity.setRoutePoints(dto.getRoutePoints());
		entity.setDuration(dto.getDuration());

		return entity;
	}
}
