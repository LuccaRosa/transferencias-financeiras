package com.tokiomarine.transferenciasfinanceiras.controller;

import com.tokiomarine.transferenciasfinanceiras.controller.data.TransacaoRequest;
import com.tokiomarine.transferenciasfinanceiras.controller.data.TransacaoResponse;
import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import com.tokiomarine.transferenciasfinanceiras.exception.TaxacaoException;
import com.tokiomarine.transferenciasfinanceiras.service.TransacaoService;
import com.tokiomarine.transferenciasfinanceiras.service.converter.TransacaoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transacao")
@CrossOrigin("*")
public class TransacoesController {
    private final TransacaoService transacaoService;
    private final TransacaoConverter converter;

    public TransacoesController(TransacaoService transacaoService, TransacaoConverter converter) {
        this.transacaoService = transacaoService;
        this.converter = converter;
    }
    @PostMapping
    public ResponseEntity<?> criaTransacao(@RequestBody  TransacaoRequest transacaoRequest) throws TaxacaoException {
        try{
            Transacao transacaoCriada = transacaoService
                    .criarTransacao(converter.toTransacaoEntity(transacaoRequest));
            return ResponseEntity.status(201).body(transacaoCriada);
        } catch (TaxacaoException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> listaTransacao(){
        List<Transacao> listaTransacao = transacaoService.buscarTodasTransacoes();
        return ResponseEntity.status(200).body(converter.fromTransacaoEntity(listaTransacao));
    }
}
