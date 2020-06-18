package com.ibgregorio.capbank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibgregorio.capbank.domain.Conta;
import com.ibgregorio.capbank.repositories.ContaRepository;
import com.ibgregorio.capbank.services.exception.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repo;
	
	public Conta find(Integer id) {
		Optional<Conta> conta = repo.findById(id);
		
		return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada: " + id));
	}
	
	public Conta findByNumConta(Integer numConta) {
		Optional<Conta> conta = repo.findByNumConta(numConta);
		
		return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada: " + numConta));
	}
	
	public Conta update(Conta contaAtualizada) {
		Conta conta = find(contaAtualizada.getId());
		conta.setSaldo(contaAtualizada.getSaldo());
		
		return repo.save(conta);
	}
	
}
