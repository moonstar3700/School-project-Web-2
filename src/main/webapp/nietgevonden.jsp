<%--
  Created by IntelliJ IDEA.
  User: smigi
  Date: 09/03/2021
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reading club - Niet gevonden</title>
    <link rel="stylesheet" href="css/stijl.css">

</head>
<body>
<header>
    <nav>
        <ul>
            <li >
                <a href="BoekForm">Home</a>
            </li>
            <li>
                <a href="BoekForm?command=BoekToevoegen">Boek Toevoegen</a>
            </li>
            <li>
                <a href="BoekForm?command=Overzicht">Overzicht</a>
            </li>
            <li class="hier">
                <a href="BoekForm?command=searchPage">Zoek</a>
            </li>
            <li>
                <a href="Logboek.jsp">Logboek</a>
            </li>
        </ul>
    </nav>
</header>
<main>
    <section>
        <h1>Het boek dat u zocht staat niet in de lijst</h1>
    </section>
</main>
<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>
</html>
