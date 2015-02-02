
<%@page import="hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Inmueble inmueble =(Inmueble)request.getAttribute("inmueble");
    
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir foto</title>
    </head>
    <body>
        <h1>Subir Archivo</h1>
        <form action="control" method="post" enctype="multipart/form-data">
            <input type="file" name="archivo" />
            <input type="hidden" name="id" value="<%= inmueble.getId()%>" />
            <input type="hidden" name="target" value="inmueble" />
            <input type="hidden" name="op" value="addfoto" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="Subir" />
            
        </form>
    </body>
</html>