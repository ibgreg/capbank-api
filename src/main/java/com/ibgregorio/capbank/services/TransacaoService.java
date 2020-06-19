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
	
	public Transacao insertSaque(Transacao transacao) {
		transacao.setTipo(TipoTransacao.DEBITO);
		transacao = insert(transacao);
		
		atualizaSaldoConta(transacao.getConta(), transacao);
		
		return transacao;
	}
	
	public Transacao insertDeposito(Transacao transacao) {		
		Transacao transacaoOrigem = new Transacao(null, transacao.getContaOrigem(), transacao.getConta(), transacao.getValor(), TipoTransacao.DEBITO, transacao.getOperacao(), null);
		transacaoOrigem = insert(transacaoOrigem);
		atualizaSaldoConta(transacaoOrigem.getConta(), transacaoOrigem);

		transacao.setTipo(TipoTransacao.CREDITO);
		transacao = insert(transacao);
		atualizaSaldoConta(transacao.getConta(), transacao);
		
		return transacao;
	}
	
	@Transactional
	private Transacao insert(Transacao transacao) {
		transacao.setId(null);		
		transacao.setDataTransacao(new Date(System.currentTimeMillis()));
		
		transacao = repo.save(transacao);
		
		return transacao;
	}
	
	@Transactional
	private void atualizaSaldoConta(Conta conta, Transacao transacao) {
		Double novoSaldo;
		
		if (transacao.getTipo().equals(TipoTransacao.CREDITO)) {
			novoSaldo = conta.getSaldo() + transacao.getValor();			
		} else {
			novoSaldo = conta.getSaldo() - transacao.getValor();
		}
		
		conta.setSaldo(novoSaldo);
		contaService.update(conta);
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
