package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDatos {
	private Connection conexion;

	public BaseDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conex = "jdbc:mysql://localhost:3306/biblioteca_online";
			this.conexion = DriverManager.getConnection(conex,"root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean compruebaUsuario(String usuario, String password) {
		boolean check = false;
		try {
			Statement s = this.conexion.createStatement();
			String sql = "SELECT count(*) FROM USUARIOS where usuario='"+usuario+"' "
					+ "and password='"+password+"'";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			rs.next();
			if (rs.getInt(1)>0)
				check=true;
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		return check ;
	}

	public ArrayList<Libro> consultaLibros(String filtro) {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		try {
			Statement s = this.conexion.createStatement();
			String sql = "SELECT * FROM LIBROS where TITULO LIKE '%"+filtro+"%'";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7));
				lista.add(libro);
			}
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		return lista ;
	}
	
	public void insertarLibro(Libro libro) {
		// TODO Auto-generated method stub
		String query = "insert into libros (id, titulo, autor, editorial, fecha, categoria, novedad)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt;
			preparedStmt = this.conexion.prepareStatement(query);
			preparedStmt.setInt(1, libro.getId());
			preparedStmt.setString(2, libro.getTitulo());
			preparedStmt.setString(3, libro.getAutor());
			preparedStmt.setString(4, libro.getEditorial());

			java.sql.Date  sqlDate = new java.sql.Date(libro.getFecha().getTime());
			preparedStmt.setDate(5, sqlDate);
			preparedStmt.setString(6, libro.getCategoria());
			preparedStmt.setInt(7, libro.getNovedad());
			preparedStmt.executeUpdate();
			
		}  catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public void eliminarLibro(String id) {
		// TODO Auto-generated method stub
		String query = " delete from libros where id="+id;
		try {
			PreparedStatement preparedStmt = this.conexion.prepareStatement(query);
			preparedStmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}	
	}
	
	public Libro recuperarLibro(String id) {
		Libro libro = null;
		try {
			Statement s = this.conexion.createStatement();
			String sql = "SELECT * FROM LIBROS where ID="+id;
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			rs.next();
			libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getDate(5), rs.getString(6), rs.getInt(7));	
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		return libro;
	}

	public void modificarLibro(Libro libro) {
		// TODO Auto-generated method stub
		try {
			Statement actua = this.conexion.createStatement();		
			String query ="update libros set id='"+libro.getId()+"',"
					+ "titulo='"+libro.getTitulo()+"',"
					+ "autor='"+libro.getAutor()+"',"
					+ "editorial='"+libro.getEditorial()+"',"
					+ "fecha='"+libro.getFecha()+"',"
					+ "categoria='"+libro.getCategoria()+"',"
					+ "novedad='"+libro.getNovedad()+"' where id='"+libro.getId()+"';";
			actua.execute(query);
			
		}  catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
}
