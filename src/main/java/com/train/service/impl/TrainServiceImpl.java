package com.train.service.impl;

import com.train.dto.TrainDTO;
import com.train.entity.TicketEntity;
import com.train.entity.TrainEntity;
import com.train.repository.TrainRepository;
import com.train.service.TrainService;
import com.train.service.converter.TrainDTOConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TrainServiceImpl implements TrainService
{
	private final TrainRepository trainRepository;
	private final TrainDTOConverter converter;

	@Autowired
	public TrainServiceImpl(TrainRepository trainRepository, TrainDTOConverter converter)
	{
		this.trainRepository = trainRepository;
		this.converter = converter;
	}

	@Override
	public ResponseEntity create(TrainDTO dto)
	{
		TrainEntity entity = converter.convertToEntity(dto);
		entity.setFreeSeats(setTrainSeatingNumbers(Integer.parseInt(entity.getSeatingCapacity())));
		trainRepository.save(entity);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@Override
	public List<TrainDTO> getAll()
	{
		return trainRepository.findAll().stream().
				map(converter::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity delete(final Long id)
	{
		trainRepository.deleteById(id);

		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public Optional<TrainEntity> getOne(final Long id)
	{
		return trainRepository.findById(id);
	}

	@Override
	public TrainDTO getTrainDto(Long id)
	{
		Optional<TrainEntity> trainEntity = getOne(id);
		return trainEntity.map(converter::convertToDTO).orElse(null);
	}

	@SneakyThrows
	@Override
	public List<TrainDTO> getByParams(final String fromPoint, final String toPoint, final String date)
	{
		List<TrainEntity> trainEntities = trainRepository.findTrains(fromPoint, toPoint, date);
		if (!trainEntities.isEmpty())
		{
			List<TrainEntity> result = new ArrayList<>();
			String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			Time currentTime = new Time(new Date().getTime());
			for (TrainEntity train : trainEntities)
			{
				if (train.getExitDate().equals(currentDate))
				{
					Time exitTime = new Time(new SimpleDateFormat("HH:mm").parse(train.getExitTime()).getTime());
					if (exitTime.after(currentTime))
					{
						result.add(train);
					}
				}
			}
			return result.stream().
					map(converter::convertToDTO).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	@Override
	public void updateTrainAfterReservation(TicketEntity ticketEntity)
	{
		Optional<TrainEntity> trainOptional = getOne(ticketEntity.getTrain().getId());
		if (trainOptional.isPresent())
		{
			TrainEntity train = trainOptional.get();
			train.setFreeSeats(deleteSeatingNumbers(train.getFreeSeats(), ticketEntity.getSeats()));
			int seatingCapacity = Integer.parseInt(train.getSeatingCapacity()) - ticketEntity.getNumberPerson();
			train.setSeatingCapacity(String.valueOf(seatingCapacity));
		}
	}

	private String setTrainSeatingNumbers(int capacity)
	{
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= capacity; i++)
		{
			result.append(i).append(",");
		}
		return result.toString().substring(0, result.length() - 1);
	}

	private String deleteSeatingNumbers(String allSeatingNumbers, String reservedSeatingNumbers)
	{
		String[] seatingNumbers = allSeatingNumbers.split(",");
		List<String> seatingNumbersList = new ArrayList<>(Arrays.asList(seatingNumbers));
		String[] reserved = reservedSeatingNumbers.split(",");

		seatingNumbersList.removeAll(Arrays.asList(reserved));

		StringBuilder result = new StringBuilder();
		for (String number : seatingNumbersList)
		{
			result.append(number).append(",");
		}

		return result.toString().substring(0, result.length() - 1);
	}
}
