package com.train.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainDTO
{
	private Long id;

	private String fromPoint;

	private String toPoint;

	private String exitDate;

	private String arrivalDate;

	private String exitTime;

	private String arrivalTime;

	private String type;

	private String cost;

	private String seatingCapacity;

	private String routePoints;

	private String duration;
}
