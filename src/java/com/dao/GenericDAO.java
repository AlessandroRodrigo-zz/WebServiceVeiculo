/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.util.List;

/**
 *
 * @author ALESSANDRORODRIGOFRE
 */
public interface GenericDAO {

    public String Cadastrar(Object objeto);

    public String Inserir(Object objeto);

    public String Alterar(Object objeto);

    public String Excluir(Object objeto);

    public Object Carregar(int Numero);

    public List<Object> Listar();
}
