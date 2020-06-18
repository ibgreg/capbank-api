package com.ibgregorio.capbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibgregorio.capbank.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

}
