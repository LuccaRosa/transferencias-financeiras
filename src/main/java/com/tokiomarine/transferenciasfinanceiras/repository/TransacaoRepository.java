package com.tokiomarine.transferenciasfinanceiras.repository;

import com.tokiomarine.transferenciasfinanceiras.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
