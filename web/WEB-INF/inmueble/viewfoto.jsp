
<%@page import="modelo.ModeloFoto"%>
<%@page import="hibernate.Inmueble"%>
<%@page import="hibernate.Fotos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Inmueble inmueble =(Inmueble)request.getAttribute("inmueble");
    String id= String.valueOf(inmueble.getId());
    List <Fotos> lista = ModeloFoto.getAll(id);
    String ruta =  "imagenes/";
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fotos</title>
    </head>
    <body>
        <table border="1">
            <h1>Fotos del inmueble <%= id.toString() %></h1>
            <tbody>
                <%
                   for(Fotos f: lista){
                %>
                <tr>
                    <td><img src="<%= ruta+f.getUrl()%>"></td>
                    <td><a href="control?target=inmobiliaria&op=deletefoto&action=op&id=<%=f.getId()%>">Borrar</a></td>
                </tr>
                <%
                    }
                %>
                
            </tbody>
        </table>
    </body>
</html>