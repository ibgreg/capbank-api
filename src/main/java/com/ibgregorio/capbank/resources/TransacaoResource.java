package com.ibgregorio.capbank.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibgregorio.capbank.domain.Transacao;
import com.ibgregorio.capbank.dto.TransacaoDTO;
import com.ibgregorio.capbank.services.TransacaoService;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoResource {

	@Autowired
	private TransacaoService service;
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST)
	public ResponseEntity<Void> insertSaque(@Valid @RequestBody TransacaoDTO transacaoDto) {
		Transacao transacao = service.dtoToEntity(transacaoDto);
		transacao = service.insertSaque(transacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(transacao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/deposito", method = RequestMethod.POST)
	public ResponseEntity<Void> insertDeposito(@Valid @RequestBody TransacaoDTO transacaoDto) {
		Transacao transacao = service.dtoToEntity(transacaoDto);
		transacao = service.insertDeposito(transacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(transacao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
