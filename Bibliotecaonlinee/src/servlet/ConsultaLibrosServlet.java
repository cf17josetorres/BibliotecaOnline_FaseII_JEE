package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ConsultaLibrosServlet")
public class ConsultaLibrosServlet extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, ParseException {
		//String titulo = request.getParameter("titulo");
		BaseDatos db = new BaseDatos();
		String boton = request.getParameter("submit");
		String filtro="";

		if (boton.equals("Consulta Libros")) {
			filtro = request.getParameter("titulo");
		} else if (boton.equals("Insertar Libro")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = Date.valueOf(request.getParameter("fecha"));
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			Libro libro = new Libro(id, titulo, autor, editorial, fecha,categoria, novedad);
			db.insertarLibro(libro);
		} else if (boton.equals("Eliminar Libros")) {
			String[] ids = request.getParameterValues("eliminados");
			for(String id:ids) {
				db.eliminarLibro(id);
			}
		}  else if (boton.equals("Recuperar Libro")) {
			String[] ids = request.getParameterValues("recuperados");
			Libro libro = db.recuperarLibro(ids[0]);
			request.setAttribute("libro",libro);
			request.setAttribute("FlagModificar", 1);
		} else if (boton.equals("Actualiza Libro")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date parsedate = format.parse(request.getParameter("fecha"));
			java.sql.Date fecha = new java.sql.Date(parsedate.getTime());

			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			Libro libro = new Libro(id, titulo, autor, editorial, fecha,categoria, novedad);
			db.modificarLibro(libro);
		}

		ArrayList<Libro> libros = db.consultaLibros(filtro);
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("<h1> !! Hola Mundo GET !! </h1>");
		//method = true;
		//doGet(request, response);
		try {
			doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("<h1> !! Hola Mundo POST !! </h1>");
		//method = false;
		//doPost(request, response);
		try {
			doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}
