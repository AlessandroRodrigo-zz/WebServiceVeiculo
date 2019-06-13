/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author ALESSANDRORODRIGOFRE
 */
public class Veiculo {

    private int idVeiculo;
    private String marcaVeiculo;
    private String modeloVeiculo;
    private String anoVeiculo;
    private String corVeiculo;
    private String combustivelVeiculo;
    private String situacaoVeiculo;

    public Veiculo() {
    }

    public Veiculo(int idVeiculo, String marcaVeiculo, String modeloVeiculo, String anoVeiculo, String corVeiculo, String combustivelVeiculo, String situacaoVeiculo) {
        this.idVeiculo = idVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.corVeiculo = corVeiculo;
        this.combustivelVeiculo = combustivelVeiculo;
        this.situacaoVeiculo = situacaoVeiculo;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public void setCorVeiculo(String corVeiculo) {
        this.corVeiculo = corVeiculo;
    }

    public String getCombustivelVeiculo() {
        return combustivelVeiculo;
    }

    public void setCombustivelVeiculo(String combustivelVeiculo) {
        this.combustivelVeiculo = combustivelVeiculo;
    }

    public String getSituacaoVeiculo() {
        return situacaoVeiculo;
    }

    public void setSituacaoVeiculo(String situacaoVeiculo) {
        this.situacaoVeiculo = situacaoVeiculo;
    }

}
