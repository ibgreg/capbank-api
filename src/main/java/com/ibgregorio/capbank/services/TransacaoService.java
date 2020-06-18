package com.ibgregorio.capbank.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibgregorio.capbank.domain.Conta;
import com.ibgregorio.capbank.domain.Transacao;
import com.ibgregorio.capbank.domain.enums.Operacao;
import com.ibgregorio.capbank.domain.enums.TipoTransacao;
import com.ibgregorio.capbank.dto.TransacaoDTO;
import com.ibgregorio.capbank.repositories.TransacaoRepository;
import com.ibgregorio.capbank.services.exception.ObjectNotFoundException;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repo;
	
	@Autowired
	private ContaService contaService;
	
	public Transacao find(Integer id) {
		Optional<Transacao> transacao = repo.findById(id);
		
		return transacao.orElseThrow(() -> new ObjectNotFoundException("Transacao não encontrada: " + id));
	}
	
	@Transactional
	public Transacao insert(Transacao transacao) {
		transacao.setId(null);		
		transacao.setTipo(TipoTransacao.DEBITO);
		transacao.setDataTransacao(new Date(System.currentTimeMillis()));
		
		transacao = repo.save(transacao);
		
		Conta contaDestino = transacao.getConta();
		Double novoSaldo = contaDestino.getSaldo() - transacao.getValor();
		
		contaDestino.setSaldo(novoSaldo);
		contaService.update(contaDestino);
		
		return transacao;
	}
	
	public Transacao dtoToEntity(TransacaoDTO transacaoDto) {
		Conta conta = contaService.findByNumConta(transacaoDto.getConta());
		Conta contaOrigem = null;
		
		if (transacaoDto.getOperacao().equals(Operacao.DEPOSITO.getCodigo())) {
			contaOrigem = contaService.findByNumConta(transacaoDto.getContaOrigem());			
		}
		
		Transacao entity = new Transacao(null, conta, contaOrigem, transacaoDto.getValor(), null, Operacao.toEnum(transacaoDto.getOperacao()), null);
		
		return entity;
	}
}
