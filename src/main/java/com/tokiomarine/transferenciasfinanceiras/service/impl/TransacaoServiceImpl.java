package com.tokiomarine.transferenciasfinanceiras.service.impl;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.repository.TransacaoRepository;
import com.tokiomarine.transferenciasfinanceiras.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao criarTransacao(Transacao transacao) throws TaxacaoException {
        transacao.setTaxa(new TaxacaoPorValorImpl().calcularTaxa(transacao));
        return transacaoRepository.save(transacao);
    }

    @Override
    public List<Transacao> buscarTodasTransacoes() {
        return transacaoRepository.findAll();
    }
}
