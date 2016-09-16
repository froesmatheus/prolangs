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
import models.Usuario;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {

    private final Connection con;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        con = new ConnectionFactory().getConnection();
    }

    public boolean insert(Usuario usuario) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "insert into usuarios(login, senha) "
                    + "values (?, ?)");

            smt.setString(1, usuario.getLogin());
            smt.setString(2, usuario.getSenha());

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean update(Usuario usuario) {
        try {
            PreparedStatement smt = con.prepareStatement(""
                    + "update usuarios set login=?, senha=? "
                    + "where id_usuario=?");

            smt.setString(1, usuario.getLogin());
            smt.setString(2, usuario.getSenha());
            smt.setInt(3, usuario.getId());

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement smt = con.prepareStatement("delete from usuarios where id_usuario=?");

            smt.setInt(1, id);

            int affectedRows = smt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<Usuario> select() {
        List<Usuario> lista = new ArrayList<>();

        try {
            PreparedStatement smt = con.prepareStatement("select * from usuarios");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public Usuario select(int id) {
        Usuario usuario = null;

        try {
            PreparedStatement smt = con.prepareStatement("select * from usuarios where id_usuario=?");

            smt.setInt(1, id);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
    
    public Usuario select(String login, String senha) {
        Usuario usuario = null;

        try {
            PreparedStatement smt = con.prepareStatement("select * from usuarios where login=? and senha=?");

            smt.setString(1, login);
            smt.setString(2, senha);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
}
