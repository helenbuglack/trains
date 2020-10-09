package com.train.service.impl;

import com.train.dto.TicketDTO;
import com.train.entity.TicketEntity;
import com.train.repository.TicketRepository;
import com.train.service.TicketService;
import com.train.service.converter.TicketDTOConverter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Service
public class TicketServiceImpl implements TicketService
{
	private final static String REQUEST_FOR_EMAIL_URL =
			"http://localhost:8080/mail/new?email={email}&body={body}&subject={subject}";

	private final TicketRepository ticketRepository;
	private final TicketDTOConverter ticketDTOConverter;
	private final RestTemplate restTemplate;

	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, TicketDTOConverter ticketDTOConverter)
	{
		this.ticketRepository = ticketRepository;
		this.ticketDTOConverter = ticketDTOConverter;
		this.restTemplate = new RestTemplate();
	}

	@Override
	@Transactional
	public ResponseEntity createTicket(TicketDTO ticketDTO)
	{
		TicketEntity entity = ticketDTOConverter.convertToEntity(ticketDTO);
		ticketRepository.save(entity);
		//sendRequest("helen.buglack@gmail.com", "test", "test");

		return new ResponseEntity(HttpStatus.CREATED);
	}

	private Response sendRequest(final String email, final String body, final String subject)
	{

		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		params.put("body", body);
		params.put("subject", subject);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(REQUEST_FOR_EMAIL_URL);
		ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.buildAndExpand(params).toUriString(),
				String.class, params);

		return Response.status(response.getStatusCodeValue()).build();
	}


}
