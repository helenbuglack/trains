package com.train.service.impl;

import com.train.dto.TrainDTO;
import com.train.entity.TrainEntity;
import com.train.repository.TrainRepository;
import com.train.service.TrainService;
import com.train.service.converter.TrainDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	public ResponseEntity update(final TrainDTO dto)
	{
		return null;
	}

	@Override
	public Optional<TrainEntity> getOne(final Long id)
	{
		return trainRepository.findById(id);
	}
}
