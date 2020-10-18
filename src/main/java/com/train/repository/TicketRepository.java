package com.train.repository;

import com.train.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>
{
	Optional<TicketEntity> findTicketEntityByAccount_IdOrderByIdDesc(Long accountId);

	List<TicketEntity> findAllByAccount_Id(Long accountId);
}
