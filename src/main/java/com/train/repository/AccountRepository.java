package com.train.repository;

import com.train.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
	AccountEntity findByEmail(String email);

	Page<AccountEntity> findAll(Pageable pageable);
//
//	@Query("SELECT acc FROM Account acc WHERE acc.phone LIKE CONCAT('%', :string, '%') or acc.email LIKE CONCAT('%', :string, '%') or acc.name LIKE CONCAT('%', :string, '%')")
//	Page<AccountEntity> findAccounts(@Param("string") String param,Pageable pageable);

	List<AccountEntity> findAccountByEmailOrPhone(String email, String phone);
}
