package com.train.service;

import com.train.dto.TrainDTO;
import com.train.entity.TrainEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface TrainService
{
	ResponseEntity create(TrainDTO dto);

	List<TrainDTO> getAll();

	ResponseEntity delete(Long id);

	ResponseEntity update(TrainDTO dto);

	Optional<TrainEntity> getOne(Long id);
}
