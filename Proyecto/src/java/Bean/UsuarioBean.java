/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import Dao.UsuarioDao;
import Modelo.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jean
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    
 
    Usuario usuario;
    List<Usuario> usuarios;
    
    
    public UsuarioBean() {
        usuario = new Usuario();
    }

    
    public void insertar(){
        
        System.out.println("nombre usuario bean:" +usuario.getNombre());
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.insertarUsuario(usuario);
        usuario= new Usuario();
        System.out.println("nobmre: "+usuario.getNombre());
        
    }
    public void modificar(){
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.modificarUsuario(usuario);
        usuario= new Usuario();
    }
    public void eliminar(){
        System.out.println("nombre usuario bean:" +usuario.getRut());
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.eliminarUsuario(usuario);
        usuario= new Usuario();
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
       UsuarioDao linkDAO= new UsuarioDao();
        usuarios=linkDAO.mostrarUsuarios();
        return usuarios;
    }
    

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
 
    
}
