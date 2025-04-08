<%@ page import="java.util.List" %>
<%@ page import="models.*" %>

<%
    List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <a href="depForm">insert depense</a>
    <a href="./">insert credit</a>
    <a href="depense">all depense</a>
    <a href="test">login</a>
    <table>
        <tr>
            <th>
                libelle
            </th>
            <th>
                montant
            </th>
        </tr>
        <%
        for(Depense d : depenses){
            out.print(d.table());
        }
        %>
    </table>
</body>
</html>