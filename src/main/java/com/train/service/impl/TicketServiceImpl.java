package com.train.service.impl;

import com.train.dto.TicketDTO;
import com.train.entity.TicketEntity;
import com.train.repository.TicketRepository;
import com.train.service.AccountService;
import com.train.service.TicketService;
import com.train.service.TrainService;
import com.train.service.converter.TicketDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService
{
	private final static String REQUEST_FOR_EMAIL_URL =
			"http://localhost:8080/mail/new?email={email}&fromPoint={fromPoint}&toPoint={toPoint}"
					+ "&exitDate={exitDate}&arrivalDate={arrivalDate}&name={name}&passport={passport}&seats={seats}"
					+ "&subject={subject}";

	private final TicketRepository ticketRepository;
	private final TrainService trainService;
	private final AccountService accountService;
	private final TicketDTOConverter ticketDTOConverter;
	private final RestTemplate restTemplate;

	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, TrainService trainService,
			AccountService accountService, TicketDTOConverter ticketDTOConverter)
	{
		this.ticketRepository = ticketRepository;
		this.trainService = trainService;
		this.accountService = accountService;
		this.ticketDTOConverter = ticketDTOConverter;
		this.restTemplate = new RestTemplate();
	}

	@Override
	@Transactional
	public ResponseEntity createTicket(TicketDTO ticketDTO)
	{
		TicketEntity entity = ticketDTOConverter.convertToEntity(ticketDTO);
		ticketRepository.save(entity);
		trainService.updateTrainAfterReservation(entity);
//		sendRequest(entity.getAccount().getEmail(), entity, "Confirmation message");

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public TicketDTO getLatestTicket()
	{
		Long accountId = accountService.getCurrentAccount().getId();
		Optional<TicketEntity> entityOptional = ticketRepository.findTicketEntityByAccount_IdOrderByIdDesc(accountId);

		return entityOptional.map(ticketDTOConverter::convertToDTO).orElse(null);
	}

	@Override
	@Transactional
	public List<TicketDTO> findAll()
	{
		Long accountId = accountService.getCurrentAccount().getId();
		List<TicketEntity> ticketEntityList = ticketRepository.findAllByAccount_Id(accountId);
		if (!CollectionUtils.isEmpty(ticketEntityList))
		{
			return ticketEntityList.stream().map(ticketDTOConverter::convertToDTO).collect(Collectors.toList());
		}
		return null;
	}

	private Response sendRequest(final String email, final TicketEntity ticket, final String subject)
	{
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		params.put("fromPoint", ticket.getFromPoint());
		params.put("toPoint", ticket.getToPoint());
		params.put("exitDate", ticket.getTrain().getExitDate() + " " + ticket.getTrain().getExitTime());
		params.put("arrivalDate", ticket.getTrain().getArrivalDate() + " " + ticket.getTrain().getArrivalTime());
		params.put("name", ticket.getName() + " " + ticket.getSurname());
		params.put("passport", ticket.getPassport());
		params.put("seats", ticket.getSeats());
		params.put("subject", subject);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(REQUEST_FOR_EMAIL_URL);
		ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.buildAndExpand(params).toUriString(),
				String.class, params);

		return Response.status(response.getStatusCodeValue()).build();
	}
}
