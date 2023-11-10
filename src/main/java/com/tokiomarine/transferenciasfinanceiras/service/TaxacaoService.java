package com.tokiomarine.transferenciasfinanceiras.service;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;

import java.math.BigDecimal;

public interface TaxacaoService {
    BigDecimal calcularTaxa(Transacao transacao) throws TaxacaoException;
}
