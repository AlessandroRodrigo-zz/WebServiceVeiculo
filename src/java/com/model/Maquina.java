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
public class Maquina {

    private int idMaquina;
    private String marcaMaquina;
    private String modeloMaquina;
    private String valorMaquina;
    private String horimetroMaquina;
    private String situacaoMaquina;

    public Maquina() {
    }

    public Maquina(int idMaquina, String marcaMaquina, String modeloMaquina, String valorMaquina, String horimetroMaquina, String situacaoMaquina) {
        this.idMaquina = idMaquina;
        this.marcaMaquina = marcaMaquina;
        this.modeloMaquina = modeloMaquina;
        this.valorMaquina = valorMaquina;
        this.horimetroMaquina = horimetroMaquina;
        this.situacaoMaquina = situacaoMaquina;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getMarcaMaquina() {
        return marcaMaquina;
    }

    public void setMarcaMaquina(String marcaMaquina) {
        this.marcaMaquina = marcaMaquina;
    }

    public String getModeloMaquina() {
        return modeloMaquina;
    }

    public void setModeloMaquina(String modeloMaquina) {
        this.modeloMaquina = modeloMaquina;
    }

    public String getValorMaquina() {
        return valorMaquina;
    }

    public void setValorMaquina(String valorMaquina) {
        this.valorMaquina = valorMaquina;
    }

    public String getHorimetroMaquina() {
        return horimetroMaquina;
    }

    public void setHorimetroMaquina(String horimetroMaquina) {
        this.horimetroMaquina = horimetroMaquina;
    }

    public String getSituacaoMaquina() {
        return situacaoMaquina;
    }

    public void setSituacaoMaquina(String situacaoMaquina) {
        this.situacaoMaquina = situacaoMaquina;
    }

}
