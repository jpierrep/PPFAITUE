/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.UsuarioDao;
import Modelo.Usuario;
import java.io.IOException;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jean
 */
@Named(value = "loginControlador")
@javax.faces.bean.ManagedBean
public class LoginControlador {

    private Usuario usuario;
   
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public LoginControlador() {
        usuario=new Usuario();   
    }
    
    public void autenticar () throws ServletException, IOException{
        
        
        FacesContext contex = FacesContext.getCurrentInstance();
        
        System.out.println("usuario: "+usuario.getRut());
         System.out.println("pass: "+usuario.getPassword());
        UsuarioDao valida= new UsuarioDao();
        
        if (valida.existePersona(usuario)){
            System.out.println("existe en la bd");  
           contex.getExternalContext().redirect("/Proyecto/faces/welcomePrimefaces.xhtml");
            
        }else{
            
            
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario no encontrado."));
    
           
        }
        
        
    }
}
