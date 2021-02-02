package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestorBibliotecaServlet
 */
@WebServlet("/GestorBibliotecaServlet")
public class GestorBibliotecaServlet extends HttpServlet {
	private Connection conexion;

	//private static final long serialVersionUID = 1L;
	private boolean YaIniciado = false;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario"); //$_GET("");
		String password = request.getParameter("password");
		BaseDatos bd = new BaseDatos();
		//if (usuario.equals("pepe") && password.equals("pepe")) {
		if (bd.compruebaUsuario(usuario, password)) {
			boolean iniciado = YaIniciado;
			if (!YaIniciado) YaIniciado = true;
			response.sendRedirect("bienvenida.jsp?usuario="+usuario+"&iniciado="+iniciado+"&method=GET");
		} else 
			response.sendRedirect("error.jsp?usuario="+usuario);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario"); //$_POST("");
		String password = request.getParameter("password");
		BaseDatos bd = new BaseDatos();
		//if (usuario.equals("pepe") && password.equals("pepe")) {
		if (bd.compruebaUsuario(usuario, password)) {
			boolean iniciado = YaIniciado;
			if (!YaIniciado) YaIniciado = true;
			response.sendRedirect("bienvenida.jsp?usuario="+usuario+"&iniciado="+iniciado+"&method=POST");
		} else 
			response.sendRedirect("error.jsp?usuario="+usuario);	
	}
	
	public void insertarLibro(Libro libro) {
		String query = "insert into libros (id, titulo, autor, editorial, fecha, categoria, novedad)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt;
			preparedStmt = conexion.prepareStatement(query);
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

	/**
	 * Default constructor. 
	 */
	/*public GestorBibliotecaServlet() {
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("<h1> !! Hola Mundo GET !! </h1>");
		method = true;
		doGet(request, response);
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("<h1> !! Hola Mundo POST !! </h1>");
		method = false;
		doPost(request, response);
	}*/
}
