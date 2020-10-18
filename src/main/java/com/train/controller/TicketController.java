package com.train.controller;

import com.train.dto.TicketDTO;
import com.train.dto.TrainDTO;
import com.train.service.TicketService;
import com.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class TicketController
{
	private final TicketService ticketService;
	private final TrainService trainService;

	@Autowired
	public TicketController(TicketService ticketService, TrainService trainService)
	{
		this.ticketService = ticketService;
		this.trainService = trainService;
	}

	@PostMapping(value = "/ticket/new")
	public ResponseEntity createTicket(@RequestBody TicketDTO ticketDTO)
	{

		return ticketService.createTicket(ticketDTO);
	}

	@GetMapping(value = "/ticket/new")
	public ModelAndView getViewForBookingTicket(@RequestParam Long trainId)
	{
		final TrainDTO train = trainService.getTrainDto(trainId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("train", train);
		modelAndView.setViewName("createTicketPage");

		return modelAndView;
	}

	@GetMapping(value = "/ticket/success")
	public ModelAndView getViewForSuccessBookingTicket()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pageSuccess");

		return modelAndView;
	}

	@GetMapping(value = "/ticket/about")
	public ModelAndView getLatestTicket()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("aboutTicket");

		return modelAndView;
	}


}
