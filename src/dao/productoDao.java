/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.producto;

/**
 *
 * @author LN710Q
 */
public class productoDao {
    private static final String SQL_INSERT = "INSERT INTO filtros_aceite(codFiltro,marca,stock,existencia) VALUES(?,?,?,?,?,?)";
    ;
    private static final String SQL_UPDATE = "UPDATE filtros_aceite SET marca =?, stock =?, existencia =? WHERE codFiltro=?";
    private static final String SQL_DELETE = "DLETE FROM filtros_aceite WHERE codFiltro=?";
    private static final String SQL_READ = "SELECT * FROM filtros_aceite WHERE codfiltros=?";
    private static final String SQL_READALL = "SELECT * FROM filtros_aceite";

    public static final conexion con=conexion.conectar();

    
    @Override
    public boolean create(producto c) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getCodigo());
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getCantidad());
            ps.setFloat(5,c.getPrecio());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }
    
    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(producto c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getCodigo());
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getCantidad());
            ps.setFloat(5,c.getPrecio());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public producto read(Object key) {
        producto f = null;

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                f = new producto(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<producto> readAll() {
        ArrayList<producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new producto(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;

    }
}
