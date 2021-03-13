<%@ page import="domain.model.Boek" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: smigi
  Date: 07/03/2021
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Overzicht</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>

<body>
<header>
    <nav>
        <ul>
            <li>
                <a href="BoekForm">Home</a>
            </li>
            <li>
                <a href="BoekToevoegen.jsp">Boek Toevoegen</a>
            </li>
            <li class="hier">
                <a href="BoekForm?command=Overzicht">Overzicht</a>
            </li>
        </ul>
    </nav>
</header>
<main>
    <section>
        <h1>Overzicht van alle boeken</h1>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Titel boek</th>
                    <th>Autheur</th>
                    <th>Aantal pagina's</th>
                    <th>Score</th>
                    <th>Update</th>
                    <th>Verwijderen</th>
                </tr>
                </thead>
                <tbody>
                <%   ArrayList<Boek> boeken = (ArrayList<Boek>) request.getAttribute("alleboeken"); //

                    for(Boek b: boeken){
                %>
                <tr>
                    <td><%=b.getTitel()%></td>
                    <td><%=b.getAutheur()%></td>
                    <td><%=b.getPagina()%></td>
                    <td><%=b.getScore()%></td>
                    <td>Update</td>
                    <td><a href="BoekForm?command=deleteConfirmation&titel=<%=b.getTitel()%>&autheur=<%=b.getAutheur()%>">Verwijder</a></td>

                </tr>
                <%}%>
                </tbody>
            </table>
        </div>

    </section>
</main>
<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>

</html>
