package com.tokiomarine.transferenciasfinanceiras.service.impl;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.TaxacaoService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class TaxacaoAService implements TaxacaoService{
    @Override
    public BigDecimal calcularTaxa(Transacao transacao) throws TaxacaoException {
        if (diasEntreDatas(transacao) != 0) {
            throw new TaxacaoException("Não há taxação para a transação escolhida.");
        }
        return (transacao.getValorTransferencia()
                .multiply(BigDecimal.valueOf(0.03))).add(BigDecimal.valueOf(3));
    }

    private long diasEntreDatas(Transacao transacao){
        return ChronoUnit.DAYS.between(transacao.getDataAgendamento(),transacao.getDataTransferencia());
    }
}
