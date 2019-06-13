/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

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
public class VeiculoDAO implements GenericDAO {

    private Connection Connection;

    public VeiculoDAO() throws Exception {
        try {
            this.Connection = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    public String Cadastrar(Object objeto) {
        Veiculo oVeiculo = (Veiculo) objeto;
        String retorno = null;

        if (oVeiculo.getIdVeiculo() == 0) {
            retorno = this.Inserir(oVeiculo);
        } else {
            retorno = this.Alterar(oVeiculo);
        }
        return retorno;
    }

    @Override
    public String Inserir(Object objeto) {
        Veiculo oVeiculo = (Veiculo) objeto;
        PreparedStatement stmt = null;

        String strSQL = "Insert Into Veiculo (marcaVeiculo, modeloVeiculo, anoVeiculo, corVeiculo, combustivelVeiculo, situacaoVeiculo) Values (?, ?, ? , ?, ?, ?);";

        try {
            stmt = Connection.prepareStatement(strSQL);

            try {
                stmt.setString(1, oVeiculo.getMarcaVeiculo());
                stmt.setString(2, oVeiculo.getModeloVeiculo());
                stmt.setString(3, oVeiculo.getAnoVeiculo());
                stmt.setString(4, oVeiculo.getCorVeiculo());
                stmt.setString(5, oVeiculo.getCombustivelVeiculo());
                stmt.setString(6, oVeiculo.getSituacaoVeiculo());
            } catch (Exception ex) {
                System.out.println("Problemas ao cadastrar veiculo! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
            stmt.execute();
            return "Novo veiculo cadastrado com sucesso";
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar veiculo! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao cadastrar novo veiculo";
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
        Veiculo oVeiculo = (Veiculo) objeto;
        PreparedStatement stmt = null;

        String strSQL = "Update Veiculo set marcaVeiculo = ?, modeloVeiculo = ?, anoVeiculo = ?, corVeiculo = ?, combustivelVeiculo = ?, situacaoVeiculo = ? Where idVeiculo = ?;";

        try {
            stmt = Connection.prepareStatement(strSQL);
            stmt.setString(1, oVeiculo.getMarcaVeiculo());
            stmt.setString(2, oVeiculo.getModeloVeiculo());
            stmt.setString(3, oVeiculo.getAnoVeiculo());
            stmt.setString(4, oVeiculo.getCorVeiculo());
            stmt.setString(5, oVeiculo.getCombustivelVeiculo());
            stmt.setString(6, oVeiculo.getSituacaoVeiculo());
            stmt.setInt(7, oVeiculo.getIdVeiculo());

            stmt.executeUpdate();
            return "Veiculo alterado com sucesso";

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Veiculo! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao alterar veiculo";
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
        Veiculo oVeiculo = (Veiculo) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String situacao = "A";
        String mensagem = null;
        String strSQL = "select v.situacaoVeiculo from veiculo v where idVeiculo = ?";

        try {
            stmt = Connection.prepareStatement(strSQL);
            stmt.setInt(1, oVeiculo.getIdVeiculo());

            rs = stmt.executeQuery();

            while (rs.next()) {
                oVeiculo.setSituacaoVeiculo(rs.getString("situacaoveiculo"));
            }

            if (oVeiculo.getSituacaoVeiculo().equals(situacao)) {
                situacao = "I";
                mensagem = "Veiculo inativado com sucesso";
            } else {
                situacao = "A";
                mensagem = "Veiculo ativado com sucesso";
            }

            strSQL = "Update veiculo set situacaoVeiculo = ? where idVeiculo = ?";

            try {
                stmt = Connection.prepareStatement(strSQL);
                stmt.setString(1, situacao);
                stmt.setInt(2, oVeiculo.getIdVeiculo());
                stmt.execute();
                return mensagem;

            } catch (Exception e) {
                System.out.println("Problemas ao inativar Veiculo! Erro: " + e.getMessage());
                return mensagem;
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao localizar veiculo no banco! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return "Problemas ao inativar/reativar veiculo";
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
        Veiculo oVeiculo = new Veiculo();

        String strSQL = "Select v.* From veiculo v Where v.idVeiculo = ?;";
        try {
            Stmt = Connection.prepareStatement(strSQL);
            Stmt.setInt(1, Numero);
            rs = Stmt.executeQuery();

            while (rs.next()) {
                oVeiculo.setIdVeiculo(rs.getInt("idVeiculo"));
                oVeiculo.setMarcaVeiculo(rs.getString("marcaveiculo"));
                oVeiculo.setModeloVeiculo(rs.getString("modeloveiculo"));
                oVeiculo.setAnoVeiculo(rs.getString("anoveiculo"));
                oVeiculo.setCorVeiculo(rs.getString("corveiculo"));
                oVeiculo.setCombustivelVeiculo(rs.getString("combustivelveiculo"));
                oVeiculo.setSituacaoVeiculo(rs.getString("situacaoveiculo"));
            }
            return oVeiculo;

        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Veiculo! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, Stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oVeiculo;
    }

    @Override
    public List<Object> Listar() {
        List<Object> listaVeiculo = new ArrayList<>();
        PreparedStatement Stmt = null;
        ResultSet rs = null;

        String strSQL = "Select v.* from veiculo v order by v.idVeiculo";

        try {
            Stmt = Connection.prepareStatement(strSQL);
            rs = Stmt.executeQuery();

            while (rs.next()) {
                Veiculo oVeiculo = new Veiculo();
                oVeiculo.setIdVeiculo(rs.getInt("idveiculo"));
                oVeiculo.setMarcaVeiculo(rs.getString("marcaveiculo"));
                oVeiculo.setModeloVeiculo(rs.getString("modeloveiculo"));
                oVeiculo.setAnoVeiculo(rs.getString("anoveiculo"));
                oVeiculo.setCorVeiculo(rs.getString("corveiculo"));
                oVeiculo.setCombustivelVeiculo(rs.getString("combustivelveiculo"));
                oVeiculo.setSituacaoVeiculo(rs.getString("situacaoveiculo"));
                listaVeiculo.add(oVeiculo);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Veiculo! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.CloseConnection(Connection, Stmt, rs);

            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaVeiculo;
    }

}
