package com.train.repository;

import com.train.entity.RoleEntity;
import com.train.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity , Long>
{
	RoleEntity findByRole(Roles role);
}
