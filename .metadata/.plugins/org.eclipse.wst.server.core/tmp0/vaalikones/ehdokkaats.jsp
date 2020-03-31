<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%List <Ehdokkaat> e=(List <Ehdokkaat>)request.getAttribute("Ehdokkaat"); %>



<body>
<table>

<% for(Ehdokkaat eh:e){ %>
<form action="admin" method="get">
<tr>
<td><input type="text" redonly="false" name="Id" value=<%=eh.getEhdokasId() %>></td>
<td><input type="text" name="Sukunimi" value=<%=eh.getSukunimi() %>></td>
<td><input type="text" name="Etunimi" value=<%=eh.getEtunimi() %>></td>
<td><input type="submit" name="Edit" value="Edit"></td>
<td><input type="submit" name="Del" value="Delete"></td>
</tr>
</form>
<%}%>

</table>




</body>
</html>