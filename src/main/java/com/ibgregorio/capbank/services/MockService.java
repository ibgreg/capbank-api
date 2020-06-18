package com.ibgregorio.capbank.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibgregorio.capbank.domain.Cliente;
import com.ibgregorio.capbank.domain.Conta;
import com.ibgregorio.capbank.domain.Transacao;
import com.ibgregorio.capbank.domain.enums.TipoTransacao;
import com.ibgregorio.capbank.repositories.ClienteRepository;
import com.ibgregorio.capbank.repositories.ContaRepository;
import com.ibgregorio.capbank.repositories.TransacaoRepository;

@Service
public class MockService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	public void loadMockDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfHour = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Cliente cli1 = new Cliente(null, "Fábio Cavalcanti", "37407239207", sdf.parse("05/09/1950"));
		Cliente cli2 = new Cliente(null, "Daniela Julia", "40938140906", sdf.parse("04/02/1965"));
		Cliente cli3 = new Cliente(null, "Vitor Moura", "65201263810", sdf.parse("12/11/1970"));
		Cliente cli4 = new Cliente(null, "Mário Oliver", "41273579755", sdf.parse("20/05/1988"));
		Cliente cli5 = new Cliente(null, "Letícia Isabel", "61470208970", sdf.parse("22/12/1984"));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		
		Conta cont1 = new Conta(null, 3479, 782342, cli1, 7432.0);
		Conta cont2 = new Conta(null, 3479, 562231, cli2, 0.0);
		Conta cont3 = new Conta(null, 5731, 379655, cli3, 250.0);
		Conta cont4 = new Conta(null, 9010, 982093, cli4, 1400.0);
		Conta cont5 = new Conta(null, 7322, 192003, cli5, 720.0);
		
		contaRepository.saveAll(Arrays.asList(cont1, cont2, cont3, cont4, cont5));
		
		Transacao tran1 = new Transacao(null, cont2, 150.0, TipoTransacao.DEBITO, sdfHour.parse("15/03/2020 09:42"));
		Transacao tran2 = new Transacao(null, cont2, 1000.0, TipoTransacao.DEBITO, sdfHour.parse("20/04/2020 17:10")); 
		Transacao tran3 = new Transacao(null, cont1, 1000.0, TipoTransacao.CREDITO, sdfHour.parse("12/06/2020 11:30")); 
		Transacao tran4 = new Transacao(null, cont4, 70.0, TipoTransacao.CREDITO, sdfHour.parse("01/04/2020 20:00")); 
		Transacao tran5 = new Transacao(null, cont5, 70.0, TipoTransacao.DEBITO, sdfHour.parse("27/05/2020 15:10"));
		
		transacaoRepository.saveAll(Arrays.asList(tran1, tran2, tran3, tran4, tran5));
	}
}
