package com.tokiomarine.transferenciasfinanceiras.service.converter;

import com.tokiomarine.transferenciasfinanceiras.controller.data.TransacaoRequest;
import com.tokiomarine.transferenciasfinanceiras.controller.data.TransacaoResponse;
import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoConverter {
    public Transacao toTransacaoEntity(TransacaoRequest transacaoRequest){
        float valorTransferencia = Float.valueOf(transacaoRequest.valorTransferencia());
        return new Transacao(transacaoRequest.contaOrigem(),
                transacaoRequest.contaDestino(),
                LocalDate.now(),
                transacaoRequest.dataAgendamento(),
                BigDecimal.valueOf(valorTransferencia));
    }
    
    public List<TransacaoResponse> fromTransacaoEntity(List<Transacao> transacao){
        List<TransacaoResponse> transacaoResponseList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");


        if (!transacao.isEmpty()){

            for (Transacao transacaoObj: transacao
                 ) {
                transacaoResponseList.add(
                      new TransacaoResponse(transacaoObj.getContaOrigem(), transacaoObj.getContaDestino(),transacaoObj.getDataAgendamento().format(formatter),transacaoObj.getDataTransferencia().format(formatter),transacaoObj.getValorTransferencia(),transacaoObj.getTaxa())

                );
            }
        }
        return transacaoResponseList;
    }
}
