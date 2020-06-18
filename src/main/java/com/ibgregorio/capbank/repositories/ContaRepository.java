package com.ibgregorio.capbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibgregorio.capbank.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

	@Transactional(readOnly = true)
	Optional<Conta> findByNumConta(Integer numConta);
}
