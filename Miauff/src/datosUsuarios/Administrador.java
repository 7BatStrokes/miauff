package datosUsuarios;
import java.util.ArrayList;


import datosMascotas.Mascotas;
import logicaNegocio.LectorMascotas;
import logicaNegocio.Manipular;

public class Administrador<T>  extends Usuario{
	
	
    public Administrador(String idUsuario, String contraseña, String nombre, String descripcion) {
		super(idUsuario, contraseña, nombre, descripcion);
	}  
	
	public Administrador(){
		super();
	}

	public void editarMascota(int posicion,Mascotas datos){
				
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		Manipular m= new Manipular(lm.getDatosMascotas());
        m.modificarRegistro(posicion,datos);
		m.escribirDatos(m.obtenerRegistro());
	}
	public void eliminarMascota(int posicion){
		
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas(); 
		Manipular m= new Manipular(lm.getDatosMascotas());
		m.eliminarRegistro(posicion);
		m.escribirDatos(m.obtenerRegistro());
		
	}
	public void crearMascota(ArrayList<Object> datos){
		
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		Manipular m= new Manipular(lm.getDatosMascotas());
		m.grabarNuevoDato("mascostas.txt", datos);
	}

	@Override
	public String toString() {
		return "Administrador % idUsuario=" + idUsuario  + "% contraseña=" + contraseña+ "% nombre=" + nombre + "% descripcion=" + descripcion ;
	}

}
