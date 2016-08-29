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
import models.Framework;

/**
 *
 * @author mathe
 */
public class FrameworkDAO {

    private final Connection con;

    public FrameworkDAO() throws SQLException, ClassNotFoundException {
        con = new ConnectionFactory().getConnection();
    }

    public boolean insert(Framework framework) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "insert into frameworks(nome, descricao, genero, pagina_oficial, id_linguagem, caminho_logo) "
                    + "values (?, ?, ?, ?, ?, ?)");

            smt.setString(1, framework.getNome());
            smt.setString(2, framework.getDescricao());
            smt.setString(3, framework.getGenero());
            smt.setString(4, framework.getPaginaOficial());
            smt.setInt(5, framework.getIdLinguagem());
            smt.setString(6, framework.getCaminhoLogo());

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean update(Framework framework) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "update frameworks set nome=?, descricao=?, genero=?, pagina_oficial=?, caminho_logo = ?"
                    + "where id_framework=?");

            smt.setString(1, framework.getNome());
            smt.setString(2, framework.getDescricao());
            smt.setString(3, framework.getGenero());
            smt.setString(4, framework.getPaginaOficial());
            smt.setString(5, framework.getCaminhoLogo());
            smt.setInt(6, framework.getId());

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement smt = con.prepareStatement("delete from frameworks where id_framework=?");

            smt.setInt(1, id);

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<Framework> select() {
        List<Framework> lista = new ArrayList<>();

        try {
            PreparedStatement smt = con.prepareStatement("select * from frameworks");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Framework framework = new Framework();

                framework.setId(rs.getInt("id_framework"));
                framework.setDescricao(rs.getString("descricao"));
                framework.setGenero(rs.getString("genero"));
                framework.setNome(rs.getString("nome"));
                framework.setPaginaOficial(rs.getString("pagina_oficial"));
                framework.setIdLinguagem(rs.getInt("id_linguagem"));
                framework.setCaminhoLogo(rs.getString("caminho_logo"));

                lista.add(framework);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Framework> selectPorLinguagem(int idLinguagem) {
        List<Framework> lista = new ArrayList<>();

        try {
            PreparedStatement smt = con.prepareStatement("select * from frameworks where id_linguagem=?");

            smt.setInt(1, idLinguagem);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Framework framework = new Framework();

                framework.setId(rs.getInt("id_framework"));
                framework.setDescricao(rs.getString("descricao"));
                framework.setGenero(rs.getString("genero"));
                framework.setNome(rs.getString("nome"));
                framework.setPaginaOficial(rs.getString("pagina_oficial"));
                framework.setIdLinguagem(rs.getInt("id_linguagem"));
                framework.setCaminhoLogo(rs.getString("caminho_logo"));

                lista.add(framework);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Framework> where(String query) {
        List<Framework> lista = new ArrayList<>();

        try {
            PreparedStatement smt = con.prepareStatement("select * from frameworks where lower(nome) like lower(?)");

            smt.setString(1, "%" + query + "%");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Framework framework = new Framework();

                framework.setId(rs.getInt("id_framework"));
                framework.setDescricao(rs.getString("descricao"));
                framework.setGenero(rs.getString("genero"));
                framework.setNome(rs.getString("nome"));
                framework.setPaginaOficial(rs.getString("pagina_oficial"));
                framework.setIdLinguagem(rs.getInt("id_linguagem"));
                framework.setCaminhoLogo(rs.getString("caminho_logo"));

                lista.add(framework);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public Framework select(String nome) {
        Framework framework = null;

        try {
            PreparedStatement smt = con.prepareStatement("select * from frameworks where lower(nome)=lower(?)");

            smt.setString(1, nome);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                framework = new Framework();

                framework.setId(rs.getInt("id_framework"));
                framework.setDescricao(rs.getString("descricao"));
                framework.setNome(rs.getString("nome"));
                framework.setGenero(rs.getString("genero"));
                framework.setPaginaOficial(rs.getString("pagina_oficial"));
                framework.setIdLinguagem(rs.getInt("id_linguagem"));
                framework.setCaminhoLogo(rs.getString("caminho_logo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameworkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return framework;
    }
}
