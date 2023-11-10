package com.tokiomarine.transferenciasfinanceiras.service.impl;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.TaxacaoService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class TaxacaoCService implements TaxacaoService {
    @Override
    public BigDecimal calcularTaxa(Transacao transacao) throws TaxacaoException {
        long diasEntreDatas = diasEntreDatas(transacao);
        if (diasEntreDatas <= 10) {
            throw new TaxacaoException("Não há taxação para a transação escolhida.");
        } else if ( diasEntreDatas <= 20) {
            return (transacao.getValorTransferencia()
                    .multiply(BigDecimal.valueOf(0.082)));
        } else if (diasEntreDatas <= 30) {
            return (transacao.getValorTransferencia()
                    .multiply(BigDecimal.valueOf(0.069)));
        } else if (diasEntreDatas <= 40) {
            return (transacao.getValorTransferencia()
                    .multiply(BigDecimal.valueOf(0.047)));
        }
        return (transacao.getValorTransferencia()
                .multiply(BigDecimal.valueOf(0.017)));
    }

    private long diasEntreDatas(Transacao transacao){
        return ChronoUnit.DAYS.between(transacao.getDataTransferencia(),transacao.getDataAgendamento());
    }
}
