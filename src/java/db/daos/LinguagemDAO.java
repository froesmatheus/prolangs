/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.daos;

import db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Linguagem;

/**
 *
 * @author mathe
 */
public class LinguagemDAO {
    private final Connection con;
    
    public LinguagemDAO() throws SQLException, ClassNotFoundException {
        con = new ConnectionFactory().getConnection();
    }
    
    public boolean insert(Linguagem linguagem) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "insert into linguagens(nome, descricao, licenca, logo_caminho, ano_lancamento) "
                    + "values (?, ?, ?, ?, ?)");
            
            smt.setString(1, linguagem.getNome());
            smt.setString(2, linguagem.getDescricao());
            smt.setString(3, linguagem.getLicenca());
            smt.setString(4, linguagem.getCaminhoLogo());
            smt.setString(5, linguagem.getAnoLancamento());

            int affectedRows = smt.executeUpdate();
               
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean update(Linguagem linguagem) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "update noticias set nome=?, descricao=?, licenca=?, logo_caminho=?, ano_lancamento "
                    + "where id_linguagem=?");
            
            smt.setString(1, linguagem.getNome());
            smt.setString(2, linguagem.getDescricao());
            smt.setString(3, linguagem.getLicenca());
            smt.setString(4, linguagem.getCaminhoLogo());
            smt.setString(5, linguagem.getAnoLancamento());
            smt.setInt(6, linguagem.getId());
            
            int affectedRows = smt.executeUpdate();
               
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    public boolean delete(int id) {
        try {
            PreparedStatement smt = con.prepareStatement("delete from linguagens where id_linguagem=?");
            
            smt.setInt(1, id);
            
            int affectedRows = smt.executeUpdate();
               
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List<Linguagem> select() {
        List<Linguagem> lista = new ArrayList<>();
        
        try {
            PreparedStatement smt = con.prepareStatement("select * from linguagens");
            
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                Linguagem linguagem = new Linguagem();
                
                linguagem.setId(rs.getInt("id_linguagem"));
                linguagem.setDescricao(rs.getString("descricao"));
                linguagem.setAnoLancamento(rs.getString("ano_lancamento"));
                linguagem.setLicenca(rs.getString("licenca"));
                linguagem.setCaminhoLogo(rs.getString("logo_caminho"));
                linguagem.setNome(rs.getString("nome"));
                
                lista.add(linguagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }
    
    public List<Linguagem> where(String query) {
        List<Linguagem> lista = new ArrayList<>();
        
        try {
            PreparedStatement smt = con.prepareStatement("select * from linguagens where lower(nome) like lower(?)");
            
            smt.setString(1, "%" + query + "%");
            
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                Linguagem linguagem = new Linguagem();
                
                linguagem.setId(rs.getInt("id_linguagem"));
                linguagem.setAnoLancamento(rs.getString("ano_lancamento"));
                linguagem.setCaminhoLogo(rs.getString("logo_caminho"));
                linguagem.setDescricao(rs.getString("descricao"));
                linguagem.setLicenca(rs.getString("licenca"));
                linguagem.setNome(rs.getString("nome"));
                
                lista.add(linguagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }
    
    public Linguagem select(String nome) {
        Linguagem linguagem = null;
        
        try {
            PreparedStatement smt = con.prepareStatement("select * from linguagens where lower(nome)=lower(?)");
            
            smt.setString(1, nome);
            
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                linguagem = new Linguagem();
                
                linguagem.setId(rs.getInt("id_linguagem"));
                linguagem.setAnoLancamento(rs.getString("ano_lancamento"));
                linguagem.setCaminhoLogo(rs.getString("logo_caminho"));
                linguagem.setDescricao(rs.getString("descricao"));
                linguagem.setLicenca(rs.getString("licenca"));
                linguagem.setNome(rs.getString("nome"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return linguagem;
    }
    
    public Linguagem select(int id) {
        Linguagem linguagem = null;

        try {
            PreparedStatement smt = con.prepareStatement("select * from linguagens where id_linguagem=?");

            smt.setInt(1, id);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                linguagem = new Linguagem();

                linguagem.setId(rs.getInt("id_linguagem"));
                linguagem.setAnoLancamento(rs.getString("ano_lancamento"));
                linguagem.setCaminhoLogo(rs.getString("logo_caminho"));
                linguagem.setDescricao(rs.getString("descricao"));
                linguagem.setLicenca(rs.getString("licenca"));
                linguagem.setNome(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinguagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return linguagem;
    }
}
