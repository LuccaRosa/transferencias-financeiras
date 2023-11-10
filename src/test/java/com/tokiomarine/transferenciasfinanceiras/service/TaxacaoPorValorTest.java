package com.tokiomarine.transferenciasfinanceiras.service;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.impl.TaxacaoPorValorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaxacaoPorValorTest {
    private TaxacaoPorValorImpl taxacaoPorValor;

    @BeforeEach
    public void setUp() {
        taxacaoPorValor = new TaxacaoPorValorImpl();
    }
    @Test
    public void calcularTaxa_retornaTaxacaoAService() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.valueOf(100));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(6.00).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoAServiceException() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 10),
                BigDecimal.valueOf(100));
        assertThrows(TaxacaoException.class,() ->taxacaoPorValor.calcularTaxa(transacao) );
    }

    @Test
    public void calcularTaxa_retornaTaxacaoBService() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 10),
                BigDecimal.valueOf(1001));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(12).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoBServiceException() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 20),
                BigDecimal.valueOf(100));
        assertThrows(TaxacaoException.class,() ->taxacaoPorValor.calcularTaxa(transacao) );
    }

    @Test
    public void calcularTaxa_retornaTaxacaoCServiceQuandoAgendaPor11Dias() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 20),
                BigDecimal.valueOf(2001));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(164.09).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoCServiceQuandoAgendaPor21Dias() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 30),
                BigDecimal.valueOf(2001));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(138.07).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoCServiceQuandoAgendaPor31Dias() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 12, 10),
                BigDecimal.valueOf(2001));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(94.05).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoCServiceQuandoAgendaPorMaisDe40Dias() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 12, 31),
                BigDecimal.valueOf(2001));
        BigDecimal taxacao = taxacaoPorValor.calcularTaxa(transacao);
        assertEquals(taxacao.setScale(2, BigDecimal.ROUND_UP), BigDecimal.valueOf(34.02).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    public void calcularTaxa_retornaTaxacaoCServiceException() throws TaxacaoException {
        Transacao transacao = new Transacao("123456",
                "654321",
                LocalDate.of(2023, 11, 9),
                LocalDate.of(2023, 11, 10),
                BigDecimal.valueOf(2001));
        assertThrows(TaxacaoException.class,() ->taxacaoPorValor.calcularTaxa(transacao) );
    }
}