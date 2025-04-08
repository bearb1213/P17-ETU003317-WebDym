<%@ page import="java.util.List" %>
<%@ page import="models.*" %>

<%
    List<Credit> credits = (List<Credit>) request.getAttribute("credits");

%>


<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Insert depense</h1>
    
    <a href="depForm">insert depense</a>
    <a href="./">insert credit</a>
    <a href="test">login</a>
    <a href="depense">all depense</a>
    <form action="depense" method="post">
        <label for="">credits</label>
        <select name="credit" id="">
            <%
                for(Credit c : credits){
                    out.print(c.option());
                }
            %>
        </select>
        <label for="">montant</label>
        <input type="number" name="montant" id="">
        <label for="">date</label>
        <input type="date" name="date" id="">
        <input type="submit" value="valider">
    </form>
</body>
</html>