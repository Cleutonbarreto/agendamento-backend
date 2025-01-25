package com.agendamento.app.dto;

import com.agendamento.app.entities.Agendamento;
import com.agendamento.app.enums.StatusAgendamento;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AgendamentoDTO {



    private Long id;

    private String fornecedor;

    private String produtos;

    private LocalDate data;

    private LocalTime hora;

    private Integer notaFiscal;

    private Integer quantidadeVolumes;

    private StatusAgendamento status;

    private LocalDateTime finalizado;

    public AgendamentoDTO() {
    }

    public AgendamentoDTO(Long id, String fornecedor, String produtos, LocalDate data, LocalTime hora, Integer notaFiscal, Integer quantidadeVolumes, StatusAgendamento status, LocalDateTime finalizado) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.produtos = produtos;
        this.data = data;
        this.hora = hora;
        this.notaFiscal = notaFiscal;
        this.quantidadeVolumes = quantidadeVolumes;
        this.status = status;
        this.finalizado = finalizado;
    }

    public AgendamentoDTO(Agendamento entity) {
        this.id = entity.getId();
        this.fornecedor = entity.getFornecedor();
        this.produtos = entity.getProdutos();
        this.data = entity.getData();
        this.hora = entity.getHora();
        this.notaFiscal = entity.getNotaFiscal();
        this.quantidadeVolumes = entity.getQuantidadeVolumes();
        this.status = entity.getStatus();
        this.finalizado = entity.getFinalizado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Integer getQuantidadeVolumes() {
        return quantidadeVolumes;
    }

    public void setQuantidadeVolumes(Integer quantidadeVolumes) {
        this.quantidadeVolumes = quantidadeVolumes;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;

        if (status == StatusAgendamento.FECHADO) {
            this.finalizado = LocalDateTime.now();
        } else {
            this.finalizado = null;
        }

    }

    public LocalDateTime getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(LocalDateTime finalizado) {
        this.finalizado = finalizado;
    }
}
