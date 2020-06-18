package com.ibgregorio.capbank.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibgregorio.capbank.domain.Transacao;
import com.ibgregorio.capbank.repositories.TransacaoRepository;
import com.ibgregorio.capbank.services.exception.ObjectNotFoundException;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repo;
	
	public Transacao find(Integer id) {
		Optional<Transacao> transacao = repo.findById(id);
		
		return transacao.orElseThrow(() -> new ObjectNotFoundException("Transacao não encontrada: " + id));
	}
	
	@Transactional
	public Transacao insert(Transacao transacao) {
		transacao.setId(null);
		transacao.setDataTransacao(LocalDateTime.now());
		
		transacao = repo.save(transacao);
		
		return transacao;
	}
}
