<%--
  Created by IntelliJ IDEA.
  User: smigi
  Date: 09/05/2021
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="domain.model.Boek" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reading club - logboek</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>

<body>
<jsp:include page="Header.jsp">
    <jsp:param name="hier" value="logboek"/>
</jsp:include>
<main>
    <section>
        <h1 id="logH">Logboek</h1>
        <div>
            <table>
                <thead >
                    <tr class = "logtable">
                        <th>Tijdstip</th>
                        <th>Titel</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${logboek}">
                    <tr>
                        <th>${item.time}</th>
                        <th>${item.value}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>

</body>
</html>
