package com.ibgregorio.capbank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibgregorio.capbank.domain.Cliente;
import com.ibgregorio.capbank.repositories.ClienteRepository;
import com.ibgregorio.capbank.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		
		return cliente;
	}
}
