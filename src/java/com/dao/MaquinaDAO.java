/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Maquina;
import com.model.Veiculo;
import com.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALESSANDRORODRIGOFRE
 */
public class MaquinaDAO implements GenericDAO {

    private Connection Connection;

    public MaquinaDAO() throws Exception {
        try {
            this.Connection = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    public String Cadastrar(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        String retorno = null;

        if (oMaquina.getIdMaquina() == 0) {
            retorno = this.Inserir(oMaquina);
        } else {
            retorno = this.Alterar(oMaquina);
        }
        return retorno;
    }

    @Override
    public String Inserir(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        PreparedStatement stmt = null;

        String strSQL = "Insert Into Maquina (marcaMaquina, modeloMaquina, valorMaquina, horimetroMaquina, situacaoMaquina) Values (?, ?, ?, ?, ?);";

        try {
            stmt = Connection.prepareStatement(strSQL);

            try {
                stmt.setString(1, oMaquina.getMarcaMaquina());
                stmt.setString(2, oMaquina.getModeloMaquina());
                stmt.setString(3, oMaquina.getValorMaquina());
                stmt.setString(4, oMaquina.getHorimetroMaquina());
                stmt.setString(5, oMaquina.getSituacaoMaquina());
            } catch (Exception ex) {
                System.out.println("Problemas ao cadastrar maquina! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
            stmt.execute();
            return "Nova maquina cadastrada com sucesso";
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar maquina! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao cadastrar nova maquina";
        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão de cadastro! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String Alterar(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        PreparedStatement stmt = null;

        String strSQL = "Update Maquina set marcaMaquina = ?, modeloMaquina = ?, valorMaquina = ?, horimetroMaquina = ?, situacaoMaquina = ? Where idMaquina = ?;";

        try {
            stmt = Connection.prepareStatement(strSQL);
            stmt.setString(1, oMaquina.getMarcaMaquina());
            stmt.setString(2, oMaquina.getModeloMaquina());
            stmt.setString(3, oMaquina.getValorMaquina());
            stmt.setString(4, oMaquina.getHorimetroMaquina());
            stmt.setString(5, oMaquina.getSituacaoMaquina());
            stmt.setInt(6, oMaquina.getIdMaquina());

            stmt.executeUpdate();
            return "Maquina alterada com sucesso";

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Maquina! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao alterar maquina";
        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão de alteração de veiculo! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String Excluir(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String situacao = "A";
        String mensagem = null;
        String strSQL = "select m.situacaoMaquina from maquina m where idMaquina = ?";

        try {
            stmt = Connection.prepareStatement(strSQL);
            stmt.setInt(1, oMaquina.getIdMaquina());

            rs = stmt.executeQuery();

            while (rs.next()) {
                oMaquina.setSituacaoMaquina(rs.getString("situacaomaquina"));
            }

            if (oMaquina.getSituacaoMaquina().equals(situacao)) {
                situacao = "I";
                mensagem = "Maquina inativada com sucesso";
            } else {
                situacao = "A";
                mensagem = "Maquina ativada com sucesso";
            }

            strSQL = "Update maquina set situacaoMaquina = ? where idMaquina = ?";

            try {
                stmt = Connection.prepareStatement(strSQL);
                stmt.setString(1, situacao);
                stmt.setInt(2, oMaquina.getIdMaquina());
                stmt.execute();
                return mensagem;

            } catch (Exception e) {
                System.out.println("Problemas ao inativar Maquina! Erro: " + e.getMessage());
                return mensagem;
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao localizar maquina no banco! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao inativar/reativar maquina";
        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão de inaticação/reativação de veiculo! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object Carregar(int Numero) {
        PreparedStatement Stmt = null;
        ResultSet rs = null;
        Maquina oMaquina = new Maquina();

        String strSQL = "Select m.* From maquina m Where m.idMaquina = ?;";
        try {
            Stmt = Connection.prepareStatement(strSQL);
            Stmt.setInt(1, Numero);
            rs = Stmt.executeQuery();

            while (rs.next()) {
                oMaquina.setIdMaquina(rs.getInt("idMaquina"));
                oMaquina.setMarcaMaquina(rs.getString("marcamaquina"));
                oMaquina.setModeloMaquina(rs.getString("modelomaquina"));
                oMaquina.setValorMaquina(rs.getString("valormaquina"));
                oMaquina.setHorimetroMaquina(rs.getString("horimetromaquina"));
                oMaquina.setSituacaoMaquina(rs.getString("situacaomaquina"));
            }
            return oMaquina;

        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Maquina! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, Stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oMaquina;
    }

    @Override
    public List<Object> Listar() {
        List<Object> listaMaquina = new ArrayList<>();
        PreparedStatement Stmt = null;
        ResultSet rs = null;

        String strSQL = "Select m.* from maquina m order by m.idMaquina";

        try {
            Stmt = Connection.prepareStatement(strSQL);
            rs = Stmt.executeQuery();

            while (rs.next()) {
                Maquina oMaquina = new Maquina();
                oMaquina.setIdMaquina(rs.getInt("idmaquina"));
                oMaquina.setMarcaMaquina(rs.getString("marcamaquina"));
                oMaquina.setModeloMaquina(rs.getString("modelomaquina"));
                oMaquina.setValorMaquina(rs.getString("valormaquina"));
                oMaquina.setHorimetroMaquina(rs.getString("horimetromaquina"));
                oMaquina.setSituacaoMaquina(rs.getString("situacaomaquina"));
                listaMaquina.add(oMaquina);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Maquina! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, Stmt, rs);

            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaMaquina;
    }
}
