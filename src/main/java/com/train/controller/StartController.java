package com.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class StartController
{
	@GetMapping("/")
	public ModelAndView start(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout)
	{
		ModelAndView modelAndView = new ModelAndView();
		if (error != null)
		{
			modelAndView.addObject("error", "Неправильный логин или пароль");
		}

		if (logout != null)
		{
			modelAndView.addObject("msg", "You've been logged out successfully.");
		}
		modelAndView.setViewName("welcomePage");

		return modelAndView;
	}

}
