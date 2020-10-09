package com.train.repository;

import com.train.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long>
{
	@Query("SELECT train FROM TrainEntity train WHERE (train.fromPoint LIKE CONCAT('%', :fromPoint, '%') or train.toPoint LIKE CONCAT('%', :toPoint, '%')"
			+ " or train.routePoints LIKE CONCAT('%', :fromPoint, '%') or train.routePoints LIKE CONCAT('%', :toPoint, '%')) and train.exitDate=:date")
	List<TrainEntity> findTrains(@Param("fromPoint") String fromPoint, @Param("toPoint") String toPoint,
			@Param("date") String date);


}
