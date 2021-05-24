<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
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
    <title>Reading club - Boek toevoegen</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>

<body>
<jsp:include page="Header.jsp">
    <jsp:param name="hier" value="BoekToevoegen"/>
</jsp:include>
<main>

    <section>
        <h1>
            Voeg een boek toe
        </h1>
        <c:if test="${not empty errors}">
            <div class="alerts">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>
                            ${error}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form action="BoekForm?command=voegToe" method="POST" novalidate>
            <label for="titel">Titel boek:</label><br>
            <input type="text" id="titel" name="titel" value="${titelpreviuousValue}"  required><br>

            <label for="autheur">Auteur:</label><br>
            <input type="text" id="autheur" name="autheur" value="${autheurpreviuousValue}" required><br>

            <label for="pagina">Aantal pagina's:</label><br>
            <input type="text" id="pagina" name="pagina" value="${paginapreviuousValue}" required><br>

            <label for="id_select">Score:</label><br>
            <select id="id_select" name="score">
                <option  ${param.score eq '1' ? 'selected' : ''}>1</option>
                <option  ${param.score eq '2' ? 'selected' : ''}>2</option>
                <option  ${param.score eq '3' ? 'selected' : ''}>3</option>
                <option  ${param.score eq '4' ? 'selected' : ''}>4</option>
                <option  ${param.score eq '5' ? 'selected' : ''}>5</option>
                <option  ${param.score eq '6' ? 'selected' : ''}>6</option>
                <option  ${param.score eq '7' ? 'selected' : ''}>7</option>
                <option  ${param.score eq '8' ? 'selected' : ''}>8</option>
                <option  ${param.score eq '9' ? 'selected' : ''}>9</option>
                <option  ${param.score eq '10' ? 'selected' : ''}>10</option>
            </select><br>
            <input type="submit" class="submit" value="Item toevoegen">
        </form>
    </section>
</main>
<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>

</html>
