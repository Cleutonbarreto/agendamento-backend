package com.agendamento.app.controller;

import com.agendamento.app.dto.AgendamentoDTO;
import com.agendamento.app.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "4200")
@RequestMapping(value = "/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // Buscar Todos os registros
    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos(){
        List<AgendamentoDTO> agendamento = agendamentoService.findAll();
        return ResponseEntity.ok(agendamento);
    }

    // Busca registro por Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id) {
        AgendamentoDTO dto = agendamentoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    // Criar Agendamento
    @PostMapping(value = "/salvar")
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoDTO dto){
        AgendamentoDTO createdAgendamento = agendamentoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgendamento);
    }

    //Altera registro
    @PutMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody AgendamentoDTO dto) {
        dto = agendamentoService.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
