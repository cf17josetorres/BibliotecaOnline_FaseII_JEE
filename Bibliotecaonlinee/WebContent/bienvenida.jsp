<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<%String usuario=request.getParameter("usuario");%>
		<%String iniciado=request.getParameter("iniciado");%>
		<%String method=request.getParameter("method");%>
		<%if (iniciado.equals("false")) {%>
			<h1>Conectado a la BD</h1>
		<%}%>
		<h1>BIENVENIDO USUARIO <%=usuario%> (llamada <%=method%>)</h1>
		<form method="GET" action="ConsultaLibrosServlet">
			Selección de Libro: <input type="text" name="titulo"><br><br>
			<input type="submit" value="Consultar Libros" name="submit">
		</form>
	</body>
</html>