package com.train.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "train")
@Getter
@Setter
public class TrainEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_point")
	private String fromPoint;

	@Column(name = "to_point")
	private String toPoint;

	@Column(name = "exit_date")
	private String exitDate;

	@Column(name = "arrival_date")
	private String arrivalDate;

	@Column(name = "exit_time")
	private String exitTime;

	@Column(name = "arrival_time")
	private String arrivalTime;

	@Column(name = "type")
	private String type;

	@Column(name = "cost")
	private String cost;

	@Column(name = "seating_capacity")
	private String seatingCapacity;

	@Column(name = "free_seats")
	private String freeSeats;

	@Column(name = "route_points")
	private String routePoints;

	@Column(name = "duration")
	private String duration;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,mappedBy = "account", fetch = FetchType.LAZY)
	private Set<TicketEntity> tickets = new HashSet<>();
}
