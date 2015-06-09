/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author jean
 */
public class UsuarioDao {
    
    
    public boolean existePersona(Usuario UsuarioCons)   {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

           stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? AND password= ? ");
           
            stmt.setString(1, UsuarioCons.getRut());
            stmt.setString(2, UsuarioCons.getPassword());
            rs = stmt.executeQuery();

            if (rs.next()) { //es un result set vacío 

                existe = true;


            }

        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("error"+e);
        
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
            }
        }

        return existe;
    }

     
    public void insertarUsuario(Usuario nuevoUsuario) {
        Connection con = null;
        PreparedStatement stmt = null;


        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("INSERT INTO Usuario (rut,nombre,correo,password)"
                   
                    + " values (?,?,?,?)");
          
            stmt.setString(1, nuevoUsuario.getRut());
            stmt.setString(2, nuevoUsuario.getNombre());
            stmt.setString(3, nuevoUsuario.getCorreo());
            stmt.setString(4, nuevoUsuario.getPassword());

            int retorno = stmt.executeUpdate();
            
            System.out.println("biiieennntoo");


        } catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void modificarUsuario(Usuario usuario) {
      System.out.println("usuario modif"+usuario.getRut()); 
      System.out.println("usuario modif"+usuario.getNombre()); 
      System.out.println("usuario modif"+usuario.getPassword()); 
       Connection con = null;
        PreparedStatement stmt = null;


        try {

            Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

            
            stmt = con.prepareStatement("UPDATE usuario SET nombre= ?,correo= ?,password= ? WHERE rut ='"+usuario.getRut()+"'"); 
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getPassword());
           
            int retorno = stmt.executeUpdate();
            
        } catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    
      
    }

    public void eliminarUsuario(Usuario usuario) {
        
        
        System.out.println("mandador a eliminar");
        System.out.println("usuario"+usuario.getRut());
         Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("DELETE FROM usuario WHERE rut=?");
            stmt.setString(1, usuario.getRut());
            stmt.executeUpdate();


        } catch (SQLException e) {
            
        } catch (ClassNotFoundException e) {
            
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
            }
        };
    }

    public List<Usuario> mostrarUsuarios() {
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Usuario> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Usuario");

            while (rs.next()) {
               Usuario tempUsuario = new Usuario();
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                lista.add(tempUsuario);
            } 

        } catch (SQLException e) {
           
        } catch (ClassNotFoundException e) {
            
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
            }
        }

        return lista;

    }
    
    
    
    
    
    
}
