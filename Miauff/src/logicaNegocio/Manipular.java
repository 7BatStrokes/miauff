package logicaNegocio;

import java.util.ArrayList;

import datosUsuarios.Administrador;
import datosUsuarios.Natural;
import datosUsuarios.Usuario;
import datosMascotas.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Manipular {
	private String diferenciador = "%/#";
	private String usuario ;
	private String contrasenia;
	private Natural es ;
	private Administrador est ;
	private Lector<Usuario> l  = new Lector<Usuario>("usuarios.txt");
	private ArrayList<Usuario> datos = l.getDatos() ;
	private ArrayList<Natural> datosNatu = l.getDatosPersonaNatural();
	private ArrayList<Administrador> datosAdmin = l.getDatosAdministrador();
	private Usuario borrar;
	private String[] nombres;
	private ArrayList<Mascotas> ListaDatosMascotas; 

	public Manipular(ArrayList<Mascotas>  ListaDatosMascotas){
		this.ListaDatosMascotas=ListaDatosMascotas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Manipular(String usuario, String contrasenia) {
		l.LecturaLineas();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}
	public void modificarRegistro(int i, Mascotas p){
    	this.ListaDatosMascotas.set(i, p);
    }
    
    public void eliminarRegistro(int i){
        this.ListaDatosMascotas.remove(i);
    }
    
	public ArrayList<Mascotas> obtenerRegistro(){
		return ListaDatosMascotas;
	}
	
	public void grabarNuevoDato(String nombre,ArrayList<Object> datos) {
	
		try {
			FileWriter escritor= new FileWriter("mascotas.txt", true);
			PrintWriter pw = new PrintWriter(escritor);
			pw.write(datos.get(0)+diferenciador+datos.get(1)+diferenciador+datos.get(2)+diferenciador+datos.get(3)+diferenciador+datos.get(4)+System.lineSeparator());
			pw.close();
		}catch(IOException i){
			i.printStackTrace();
		}
	}
	public void escribirDatos(ArrayList<Mascotas> datos) {
		
		try {
			FileWriter escritor= new FileWriter("mascotas.txt");
			PrintWriter pw = new PrintWriter(escritor);
			for(Mascotas i: datos) {
				pw.write(i.getNombre()+diferenciador+i.getTipo()+diferenciador+i.getRaza()+diferenciador+i.getEdad()+diferenciador+i.getFoto()+System.lineSeparator());
			}
			
			pw.close();
		}catch(IOException i){
			i.printStackTrace();
		}
	}
	
	
	
	public <E> void eliminarPersona(E tipo) {
		
		if(tipo.getClass().getSimpleName().equals("Natural")) {
			borrar=es ;
		}else {
			borrar=est;
		}
		
		datos.remove(borrar);
		l.setDatos(datos); 
		escribir();

	}
	
	public ArrayList<Administrador> getAdministrador() {
		return l.getDatosAdministrador();
	}

	public void averiguar(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		datos = new ArrayList<Usuario>();
		l.LecturaLineas();
	}
	public boolean esta() {
		for(Usuario este: datos) {
			if(este.getIdUsuario().equals(usuario) && este.getContraseña().equals(contrasenia)) {
				if(datosNatu.contains(este)) {
					int a = datosNatu.indexOf(este);
					es= datosNatu.get(a);
					return true ;	
				}else if(datosAdmin.contains(este)){{
					int b = datosAdmin.indexOf(este);
					est= datosAdmin.get(b);
					return true ;
				}				
				}				
			}
		}return false ;
	}
	
	public void actualizarNatural(Natural actualizada) {
		datos.add(actualizada);
		escribir();
		
	}public void actualizarAdministrador(Administrador cambio ) {
		datos.add(cambio);
		escribir();
	}private void escribir() {
		EscribirArchivo es = new EscribirArchivo(datos);
		es.escribirArchivo("usuarios.txt", "usuarios",false);
	}

	public Usuario getBorrar() {
		return borrar;
	}

	public void setBorrar(Usuario borrar) {
		this.borrar = borrar;
	}



	public ArrayList<Usuario> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Usuario> datos) {
		this.datos = datos;
	}

	public ArrayList<Natural> getDatosNatu() {
		return datosNatu;
	}

	public void setDatosNatu(ArrayList<Natural> datosNatu) {
		this.datosNatu = datosNatu;
	}

	public ArrayList<Administrador> getDatosAdmin() {
		return datosAdmin;
	}

	public void setDatosAdmin(ArrayList<Administrador> datosAdmin) {
		this.datosAdmin = datosAdmin;
	}

	public Natural getEs() {
		return es;
	}

	public void setEs(Natural es) {
		this.es = es;
	}

	public Administrador getEst() {
		return est;
	}

	public void setEst(Administrador est) {
		this.est = est;
	}



	
}
