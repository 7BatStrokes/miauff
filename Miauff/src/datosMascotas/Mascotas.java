package datosMascotas;

public class Mascotas {
	private String tipo;
	private String raza;
	private int edad;
	private String foto;
	private String nombre;
	public Mascotas(String nombre,String tipo,String raza,int edad, String foto){
		this.nombre = nombre;
		this.tipo=tipo;
		this.raza=raza;
		this.edad=edad;
		this.foto=foto;
		
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getFoto() {
		return foto;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}public String getNombre() {
		return this.nombre;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "# nombre-"+ nombre + ", tipo-"+ tipo + ", raza-" + raza + ", edad-" + edad + ", foto-" + foto ; 
	}
	
}
