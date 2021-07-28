package interaccionUsuarios;
import dataStructures.PilaListaEnlazada;

public abstract class Interaccion {
	
    protected String autor;
    protected Double numeroDeLikes;
    protected PilaListaEnlazada<String> contenido = new PilaListaEnlazada<String>();
    protected Double vecesCompartida ;
    protected Boolean reportado;
    
	public Interaccion(String autor, Double numeroDeLikes, PilaListaEnlazada<String> contenido2, Double vecesCompartida,
			Boolean reportado) {

		this.autor = autor;
		this.numeroDeLikes = numeroDeLikes;
		this.contenido = contenido2;
		this.vecesCompartida = vecesCompartida;
		this.reportado = reportado;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getNumeroDeLikes() {
		return numeroDeLikes;
	}

	public void setNumeroDeLikes(Double numeroDeLikes) {
		this.numeroDeLikes = numeroDeLikes;
	}

	public PilaListaEnlazada<String> getContenido() {
		return contenido;
	}

	public void setContenido(PilaListaEnlazada<String> contenido) {
		this.contenido = contenido;
	}

	public Double getVecesCompartida() {
		return vecesCompartida;
	}

	public void setVecesCompartida(Double vecesCompartida) {
		this.vecesCompartida = vecesCompartida;
	}

	public Boolean getReportado() {
		return reportado;
	}

	public void setReportado(Boolean reportado) {
		this.reportado = reportado;
	}
    
}
