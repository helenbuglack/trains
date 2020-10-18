package com.train.service;

import com.train.dto.TrainDTO;
import com.train.entity.TicketEntity;
import com.train.entity.TrainEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface TrainService
{
	ResponseEntity create(TrainDTO dto);

	List<TrainDTO> getAll();

	ResponseEntity delete(Long id);

	Optional<TrainEntity> getOne(Long id);

	TrainDTO getTrainDto(Long id);

	List<TrainDTO> getByParams(String fromPoint, String toPoint, String date);

	void updateTrainAfterReservation(TicketEntity ticketEntity);
}
