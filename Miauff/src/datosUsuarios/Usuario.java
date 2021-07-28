package datosUsuarios;


import java.util.*;

import javax.swing.Icon;
import logicaNegocio.*;
import datosMascotas.Mascotas;
public abstract class Usuario {
	
	protected String idUsuario;
	protected String contraseña; 
	protected String nombre;
	protected String descripcion;
	
	public Usuario(String idUsuario,String contraseña, String nombre,String descripcion)  {
		this.idUsuario= idUsuario;
		this.contraseña = contraseña;
		this.nombre=nombre;
		this.descripcion=descripcion;
	}
	
	public Usuario(){
		
	}
	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + "\\ numeroSeguidores=" + "\\ nombre=" + nombre + "\\ descripcion=" + descripcion + "]";
	}
}
