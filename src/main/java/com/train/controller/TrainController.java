package com.train.controller;

import com.train.dto.TrainDTO;
import com.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TrainController
{
	private final TrainService trainService;

	@Autowired
	public TrainController(TrainService trainService)
	{
		this.trainService = trainService;
	}

	@PostMapping(value = "/train/new")
	public ResponseEntity createTrainRoute(@RequestBody TrainDTO trainDTO){

		return trainService.create(trainDTO);
	}

	@GetMapping("/trains")
	public ModelAndView getTrainPage()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trains", trainService.getAll());
		modelAndView.setViewName("manageTrainsPage");

		return modelAndView;
	}

	@DeleteMapping(value = "/train/delete/{id}")
	public ResponseEntity deleteTrain(@PathVariable Long id){

		return trainService.delete(id);
	}

}
