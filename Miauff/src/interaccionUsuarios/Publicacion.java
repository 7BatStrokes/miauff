package interaccionUsuarios;

import dataStructures.PilaListaEnlazada;

import javax.swing.Icon;

public class Publicacion extends Interaccion{
    private String publicacionID;
    private String etiquetas;
    private String ubicacion;
    private Icon Imagen;
	
    public Publicacion(String autor, Double numeroDeLikes, PilaListaEnlazada<String> contenido, Double vecesCompartida,
			Boolean reportado, String publicacionID, String etiquetas, String ubicacion, Icon Imagen) {
		super(autor, numeroDeLikes, contenido, vecesCompartida, reportado);
    
		this.publicacionID=publicacionID;
		this.etiquetas=etiquetas;
		this.ubicacion=ubicacion;
		this.Imagen=Imagen;
		
	}
    public void crearPublicacion(){

    }
    public void editarPublicacion(){
        
    }
    public void eliminarPublicacion(){

    }
	public String getPublicacionID() {
		return publicacionID;
	}
	public void setPublicacionID(String publicacionID) {
		this.publicacionID = publicacionID;
	}
	public String getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Icon getImagen() {
		return Imagen;
	}
	public void setImagen(Icon imagen) {
		Imagen = imagen;
	}
    
    

    
	
    

}


