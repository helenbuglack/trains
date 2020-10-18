package com.train.service;

import com.train.dto.TicketDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TicketService
{
	ResponseEntity createTicket(TicketDTO ticketDTO);

	TicketDTO getLatestTicket();

	List<TicketDTO> findAll();
}
