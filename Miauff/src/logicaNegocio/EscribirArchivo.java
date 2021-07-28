package logicaNegocio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import datosUsuarios.Usuario;



public class EscribirArchivo {
	private ArrayList<Usuario> datos = new ArrayList<Usuario>();
	private Usuario p ;


	public EscribirArchivo(Usuario p) {
		this.p = p;
	}
	public EscribirArchivo(ArrayList<Usuario> datos) {
		this.datos= datos;
	}
	public void escribirArchivo(String nombre, String tipo, boolean escribirDebajo) {
		try {
			FileWriter escritor = new FileWriter(nombre,escribirDebajo);
			if(tipo.equals("usuario")) {
				escritor.write(p + System.lineSeparator());
			}else if (tipo.equals("usuarios")) {
				for(Usuario pe : datos) {
					escritor.write(pe + System.lineSeparator());
				}
			}

			escritor.close();
		}catch(IOException i) {
			System.out.println("No se pudo escribir en el archivo:" + nombre);
			i.printStackTrace();
		}
	}

}
