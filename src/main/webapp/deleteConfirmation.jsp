<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reading club - Bevestiging</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>
<body>
<jsp:include page="Header.jsp"/>

<main>
    <section>
        <h1>Bevestiging</h1>
        <h2>Weet u zeker dat u het volgende element wilt verwijderen?</h2>
        <p>${param.titel} van ${param.autheur}</p>

        <form action="BoekForm?command=delete&titel=${param.titel}" method="POST">
            <input type="submit" class="submit" value="Verwijder">
        </form>
        <p><a href="BoekForm?command=Overzicht">Cancel</a> indien je ${param.titel} niet wilt verwijderen?</p>
    </section>
</main>

<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>
</html>