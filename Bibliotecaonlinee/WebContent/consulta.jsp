<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.Libro"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Libros</title>
	</head>
	<body>
		<h1>LIBROS DE LA BIBLIOTECA</h1>
		<%ArrayList<Libro> libros=(ArrayList<Libro>)request.getAttribute("lista");%>
		<form action="ConsultaLibrosServlet" method="post">
		<table border=1>
		<tr><h2><td>ID<td>TITULO<td>AUTOR<td>EDITORIAL<td>FECHA<td>CATEGORIA<td>NOVEDAD<td>ELIMINAR<td>MODIFICAR<td></h2>
		<%for(Libro libro:libros){
			out.println("<tr><h3><td>"+libro.getId()+"</td>");
			out.println("<td>"+libro.getTitulo()+"</td>");
			out.println("<td>"+libro.getAutor()+"</td>");
			out.println("<td>"+libro.getEditorial()+"</td>");
			out.println("<td>"+libro.getFecha()+"</td>");
			out.println("<td>"+libro.getCategoria()+"</td>");
			out.println("<td>"+libro.getNovedad()+"</td>");
			out.println("<td><center><input type=checkbox name=borrados value="+libro.getId()+" /></center>");
			out.println("<td><center><input type=checkbox name=recuperados value="+libro.getId()+" /></center>");
		}
		%>	
		</table><br>
		<input type="submit" name="submit" value="Eliminar Libro">
		<input type="submit" name="submit" value="Recuperar Libro">
		</form>
		<% Object mod=request.getAttribute("FlagModificar");
			if(mod==null){ %>
			  <form action="ConsultaLibrosServlet" method="post">
				ID:<input type="text" name="id">
				TITULO:<input type="text" name="titulo">
				AUTOR:<input type="text" name="autor">
				EDITORIAL:<input type="text" name="editorial"><br><br>
				FECHA:<input type="text" name="fecha">
				CATEGORIA:<input type="text" name="categoria">
				NOVEDAD:<input type="text" name="novedad"><br><br>	
				<input type="submit" name="submit" value="Insertar Libro">
			  </form>		
			<%}else{
				Libro libro=(Libro)request.getAttribute("libro");%>
				<form action="ConsultaLibrosServlet" method="post">
					ID:<input type="text" name="id" value="<%=libro.getId()%>" readonly>
					TITULO:<input type="text" name="titulo" value="<%=libro.getTitulo()%>">
					AUTOR:<input type="text" name="autor" value="<%=libro.getAutor()%>">
					EDITORIAL:<input type="text" name="editorial" value="<%=libro.getEditorial()%>"><br><br>
					FECHA:<input type="text" name="fecha" value="<%=libro.getFecha()%>">
					CATEGORIA:<input type="text" name="categoria" value="<%=libro.getCategoria()%>">
					NOVEDAD:<input type="text" name="novedad" value="<%=libro.getNovedad()%>"><br><br>		
					<input type="submit" name="submit" value="Actualiza Libro">
					<input type="submit" name="submit" value="Cancelar">	
					</table><br>
				</form>
			<%}%>
	</body>
</html>