package com.ibgregorio.capbank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibgregorio.capbank.domain.Conta;
import com.ibgregorio.capbank.services.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@Autowired
	private ContaService service;
	
	@RequestMapping(value = "/{numConta}", method = RequestMethod.GET)
	public ResponseEntity<Conta> obterConta(@PathVariable Integer numConta) {
		Conta obj = service.findByNumConta(numConta);
		
		return ResponseEntity.ok().body(obj);
	}
}
