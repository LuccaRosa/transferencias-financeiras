package com.tokiomarine.transferenciasfinanceiras.service.impl;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.TaxacaoService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class TaxacaoBService implements TaxacaoService {
    @Override
    public BigDecimal calcularTaxa(Transacao transacao) throws TaxacaoException {
        if (diasEntreDatas(transacao) > 10) {
            throw new TaxacaoException("Não há taxação para a transação escolhida.");
        }
        return BigDecimal.valueOf(12);
    }

    private long diasEntreDatas(Transacao transacao){
        return ChronoUnit.DAYS.between(transacao.getDataTransferencia(),transacao.getDataAgendamento());
    }
}
