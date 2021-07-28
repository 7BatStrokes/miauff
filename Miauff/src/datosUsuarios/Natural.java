package datosUsuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logicaNegocio.*;
import javax.swing.Icon;

import dataStructures.MyArrayList;
import datosMascotas.Mascotas;

public class Natural extends Usuario{

  
    private String telefono;
    private MyArrayList<Mascotas> mascotas = new MyArrayList<Mascotas>();
    private boolean quiereAdopcion ; 
	private int numeroDeAnimales;
	
   
	public Natural(String idUsuario,String contraseña, String nombre, String descripcion,String telefono,MyArrayList<Mascotas> mascotas,boolean quiereAdopcion,int numeroDeAnimales) {
		
		super(idUsuario,contraseña,  nombre, descripcion);
        this.telefono=telefono;
        this.mascotas= mascotas;
        this.quiereAdopcion= quiereAdopcion;
		this.numeroDeAnimales=numeroDeAnimales;
	}

	 
	
	public void adoptarMascota(int numeroPosicion){
		
		
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		
		Lector l= new Lector("usuarios.txt");
		l.LecturaLineas();
		
		
		Manipular m= new Manipular(lm.getDatosMascotas());
		
	//-----	lm.getDatosMascotas().get(numeroPosicion).getTipo();

		m.eliminarRegistro(numeroPosicion);
		
	}
	
public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public MyArrayList<Mascotas> getMascotas() {
		return mascotas;
	}


	public void setMascotas(MyArrayList<Mascotas> mascotas) {
		this.mascotas =  mascotas;
	}


	public boolean isQuiereAdopcion() {
		return quiereAdopcion;
	}


	public void setQuiereAdopcion(boolean quiereAdopcion) {
		this.quiereAdopcion = quiereAdopcion;
	}


	public int getNumeroDeAnimales() {
		return numeroDeAnimales;
	}


	public void setNumeroDeAnimales(int numeroDeAnimales) {
		this.numeroDeAnimales = numeroDeAnimales;
	}


@Override
	public String toString() {
		
		return "Persona natural% idUsuario=" + idUsuario  + "% contraseña="+ contraseña+ "% nombre=" + nombre + "% descripcion=" + descripcion +  "% telefono=" + telefono + "% "+ mascotas.toString() ;
	}



public void setMascotas1(Object eliminarMascotaporindex) {
	// TODO Auto-generated method stub
	
}

}
