package com.tokiomarine.transferenciasfinanceiras.service.impl;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.TaxacaoService;

import java.math.BigDecimal;

public class TaxacaoPorValorImpl implements TaxacaoService {
    @Override
    public BigDecimal calcularTaxa(Transacao transacao) throws TaxacaoException {
        if (comparaValorTransferencia(transacao.getValorTransferencia(),BigDecimal.valueOf(1000))) {
            return new TaxacaoAService().calcularTaxa(transacao);
        } else if (comparaValorTransferencia(transacao.getValorTransferencia(),BigDecimal.valueOf(2000))) {
            return new TaxacaoBService().calcularTaxa(transacao);
        } else {
            return new TaxacaoCService().calcularTaxa(transacao);
        }
    }

    private boolean comparaValorTransferencia(BigDecimal valorTransacao,BigDecimal valorLimite) {
        int valorComparacao = valorTransacao.compareTo(valorLimite);
        return valorComparacao <= 0;
    }
}
