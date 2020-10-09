package com.train.controller;

import com.train.dto.TicketDTO;
import com.train.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketController
{
	private final TicketService ticketService;

	@Autowired
	public TicketController(TicketService ticketService)
	{
		this.ticketService = ticketService;
	}

	@PostMapping(value = "/ticket/new")
	public ResponseEntity createTicket(@RequestBody TicketDTO ticketDTO){

		return ticketService.createTicket(ticketDTO);
	}
}
