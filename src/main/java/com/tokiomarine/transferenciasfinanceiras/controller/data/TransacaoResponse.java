package com.tokiomarine.transferenciasfinanceiras.controller.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoResponse(String contaOrigem,
                                String contaDestino,
                                String dataAgendamento,
                                String dataTransferencia,
                                BigDecimal valorTransferencia,
                                BigDecimal taxa) {

}
