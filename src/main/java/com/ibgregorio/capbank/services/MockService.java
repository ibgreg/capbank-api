package com.ibgregorio.capbank.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibgregorio.capbank.domain.Cliente;
import com.ibgregorio.capbank.repositories.ClienteRepository;

@Service
public class MockService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public void loadMockDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Cliente cli1 = new Cliente(null, "Fábio Cavalcanti", "37407239207", sdf.parse("05/09/1950"));
		Cliente cli2 = new Cliente(null, "Daniela Julia", "40938140906", sdf.parse("04/02/1965"));
		Cliente cli3 = new Cliente(null, "Vitor Moura", "65201263810", sdf.parse("12/11/1970"));
		Cliente cli4 = new Cliente(null, "Mário Oliver", "41273579755", sdf.parse("20/05/1988"));
		Cliente cli5 = new Cliente(null, "Letícia Isabel", "61470208970", sdf.parse("22/12/1984"));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
	}
}
