package com.train.controller;

import com.train.dto.TrainDTO;
import com.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class TrainController
{
	private final TrainService trainService;

	@Autowired
	public TrainController(TrainService trainService)
	{
		this.trainService = trainService;
	}

	//for create and update train, if id is null train will be created
	@PostMapping(value = "/train/new")
	public ResponseEntity createTrainRoute(@RequestBody TrainDTO trainDTO)
	{

		return trainService.create(trainDTO);
	}

	@GetMapping(value = "/train/all")
	public ModelAndView getTrainPage()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trains", trainService.getAll());
		modelAndView.setViewName("manageTrainsPage");

		return modelAndView;
	}

	@DeleteMapping(value = "/train/delete/{id}")
	public ResponseEntity deleteTrain(@PathVariable Long id)
	{

		return trainService.delete(id);
	}

	@GetMapping(value = "/train/search")
	public ResponseEntity getByParams(@RequestParam String from,
			@RequestParam String to, @RequestParam String date)
	{
		List<TrainDTO> trainDTOList = trainService.getByParams(from, to, date);
		if (trainDTOList.isEmpty())
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);//if trains not found
		}

		return new ResponseEntity<>(trainDTOList, HttpStatus.OK);
	}

}
