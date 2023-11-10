package com.tokiomarine.transferenciasfinanceiras.service;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;

import java.util.List;

public interface TransacaoService {
    Transacao criarTransacao(Transacao transacao) throws TaxacaoException;
    List<Transacao> buscarTodasTransacoes();
}
