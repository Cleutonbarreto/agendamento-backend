package com.agendamento.app.services;

import com.agendamento.app.dto.AgendamentoDTO;
import com.agendamento.app.entities.Agendamento;
import com.agendamento.app.mapper.AgendamentoMapper;
import com.agendamento.app.repositories.AgendamentoRepository;
import com.agendamento.app.services.exceptions.DatabaseException;
import com.agendamento.app.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    private static final Logger log = LoggerFactory.getLogger(AgendamentoService.class);

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoMapper agendamentoMapper) {
        this.agendamentoMapper = agendamentoMapper;
    }

    // Buscar todos os Agendamento
    @Transactional(readOnly = true)
    public List<AgendamentoDTO> findAll() {
        try {
            List<Agendamento> list = agendamentoRepository.findAll();
            return list.stream().map(agendamentoMapper::toDto).collect(Collectors.toList());
        } catch (DataAccessException e) {
            log.error("Erro ao acessar o banco de dados: ", e);
            throw new DatabaseException("Falha na conexão com o banco de dados. O banco pode não estar ativo.", e);
        } catch (Exception e) {
            log.error("Erro inesperado: ", e);
            throw new DatabaseException("Erro inesperado ao acessar o banco de dados.", e);
        }
    }

    // Buscar registros por Id
    @Transactional(readOnly = true)
    public AgendamentoDTO findById(Long id) {
        log.info("Buscando agendamento com ID: " + id);
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        Agendamento entity = agendamento.orElseThrow(() -> new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado. "));
        log.info("Agendamento encontrado: " + entity);
        return agendamentoMapper.toDto(entity);
    }

    //Criar Agendamento
    @Transactional
    public AgendamentoDTO create(AgendamentoDTO dto) {
        Agendamento agendamento = agendamentoMapper.toEntity(dto);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDto(agendamento);
    }

    // Alterar Agendamento
    @Transactional
    public AgendamentoDTO update(Long id, AgendamentoDTO dto) {
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("ID no caminho e no corpo da requisição não correspondem.");
        }

        Agendamento entity = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));

        entity = agendamentoMapper.toEntity(dto);


        entity = agendamentoRepository.save(entity);
        return agendamentoMapper.toDto(entity);
    }


    public void delete(Long id) {
        agendamentoRepository.findById(id)
                .ifPresentOrElse(agendamentoRepository::delete, () -> {
                    throw new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado.");
                });
    }
}



