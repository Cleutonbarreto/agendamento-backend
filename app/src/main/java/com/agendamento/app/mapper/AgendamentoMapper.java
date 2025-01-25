package com.agendamento.app.mapper;

import com.agendamento.app.dto.AgendamentoDTO;
import com.agendamento.app.entities.Agendamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {


    AgendamentoDTO toDto(Agendamento agendamento);

    Agendamento toEntity(AgendamentoDTO agendamentoDTO);

}
