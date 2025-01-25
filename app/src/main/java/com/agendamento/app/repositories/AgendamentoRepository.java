package com.agendamento.app.repositories;

import com.agendamento.app.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository  extends JpaRepository<Agendamento, Long> {

}
