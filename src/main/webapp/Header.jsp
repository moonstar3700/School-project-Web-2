<header>
    <nav>
        <ul>
            <li ${param.hier eq 'Home'?"class = hier":""}>
                <a href="BoekForm">Home</a>
            </li>
            <li ${param.hier eq 'BoekToevoegen'?"class = hier":""}>
                <a href="BoekForm?command=BoekToevoegen">Boek Toevoegen</a>
            </li>
            <li ${param.hier eq 'Overzicht'?"class = hier":""}>
                <a href="BoekForm?command=Overzicht">Overzicht</a>
            </li>
            <li ${param.hier eq 'SearchPage'?"class = hier":""}>
                <a href="BoekForm?command=searchPage">Zoek</a>
            </li>
            <li ${param.hier eq 'logboek'?"class = hier":""}>
                <a href="BoekForm?command=logboek">Logboek</a>
            </li>

        </ul>
    </nav>
</header>
