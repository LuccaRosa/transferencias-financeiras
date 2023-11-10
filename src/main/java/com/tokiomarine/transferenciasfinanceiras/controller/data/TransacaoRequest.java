package com.tokiomarine.transferenciasfinanceiras.controller.data;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public record   TransacaoRequest(String contaOrigem,
                               String contaDestino,
                               LocalDate dataAgendamento,
                               String valorTransferencia) {
}
