<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reading club</title>
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
                <a href="BoekForm?command=BoekToevoegen">Boek Toevoegen</a>
            </li>
            <li>
                <a href="BoekForm?command=Overzicht">Overzicht</a>
            </li>
            <li>
                <a href="BoekForm?command=searchPage">Search</a>
            </li>
        </ul>
    </nav>
</header>
<main>
    <section>
        <h1>Bevestiging</h1>
        <h2>Weet u zeker dat u het volgende element wilt verwijderen?</h2>
        <p><%=request.getParameter("titel")%> van <%=request.getParameter("autheur")%></p>

        <form action="BoekForm?command=delete&titel=<%=request.getParameter("titel")%>" method="POST">
            <input type="submit" value="Verwijder">
        </form>
        <p><a href="BoekForm?command=Overzicht">Cancel</a> indien je <%=request.getParameter("titel")%> niet wilt verwijderen?</p>
    </section>
</main>

<footer>
    &copy; Patryk Piekarz, webontwikkeling 2, 2021
</footer>
</body>
</html>