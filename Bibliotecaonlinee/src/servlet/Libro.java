package servlet;

import java.sql.Date;

public class Libro {
	private int id;
	private String titulo;
	private String autor;
	private String editorial;
	private Date fecha;
	private String categoria;
	private int novedad;

	public Libro(int id, String titulo, String autor, String editorial, Date fecha, String categoria, int novedad) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.fecha = fecha;
		this.categoria = categoria;
		this.novedad = novedad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getNovedad() {
		return novedad;
	}

	public void setNovedad(int novedad) {
		this.novedad = novedad;
	}
}
