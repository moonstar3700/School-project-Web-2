<%--
  Created by IntelliJ IDEA.
  User: smigi
  Date: 07/03/2021
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reading club - Zoek</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>

<body>
<jsp:include page="Header.jsp">
    <jsp:param name="hier" value="SearchPage"/>
</jsp:include>
    <c:if test="${titelCookie != null}">
        <div>
            <p class="cook">
                Je zocht laatst naar: ${titelCookie}
            </p>
        </div>
    </c:if>
<section>
    <form action="BoekForm?command=search" method="GET">
        <label for="titel">Titel:</label><br>
        <input type="text" id="titel" name="titel"><br>
        <input type="submit" class="submit" value="Zoek">
        <input type="hidden"  name="command" value="search">
    </form>
</section>

<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>
</html>
