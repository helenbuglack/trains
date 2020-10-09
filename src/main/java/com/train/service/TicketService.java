package com.train.service;

import com.train.dto.TicketDTO;
import org.springframework.http.ResponseEntity;


public interface TicketService
{
	ResponseEntity createTicket(TicketDTO ticketDTO);
}
