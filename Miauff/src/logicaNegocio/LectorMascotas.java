package logicaNegocio;
import datosMascotas.Mascotas;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LectorMascotas {
	private String nombreArchivo;
	private ArrayList<Mascotas> datosMascotas ;
    private String diferenciador = "%/#";

	public LectorMascotas(String nombreArchivo) { 
		this.nombreArchivo= nombreArchivo;
		datosMascotas = new ArrayList<Mascotas>();
	}
	public void LecturaLineas() {
		File inFile= new File("mascotas.txt");

		try {
			Scanner sc = new Scanner(inFile);
			while(sc.hasNext()) {
				String linea = sc.nextLine();
				procesarLinea(linea);
			}
			sc.close();
		}catch (FileNotFoundException e) {
			System.err.println("No se encontro el archivo: " + nombreArchivo);
			e.printStackTrace();
		}
	}

	private void procesarLinea(String line) {
		Scanner sc = new Scanner (line);
		sc.useDelimiter(diferenciador);
		Mascotas it1=null;
		String nombre = sc.next().trim();
		String tipo = sc.next().trim();
		String raza = sc.next().trim();
		String edad =sc.next().trim();
		String foto = sc.next().trim();
		
		switch("") {
		case"":
			int edad1= Integer.parseInt(edad);
			 it1 = new Mascotas(nombre,tipo,raza, edad1, foto);
			datosMascotas.add(it1);
			break;

		default:
			System.out.println("Categoria desconocida "+ tipo);
		}
		sc.close();
	}

	public ArrayList<Mascotas> getDatosMascotas() {
		return datosMascotas;
	}
	public void setDatosMascotas(ArrayList<Mascotas> datosMascotas) {
		this.datosMascotas = datosMascotas;
	}
	

}
