package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestClass_2_functionaliteiten {

    private WebDriver driver;
        private final String ValidTitle = "Web";
        private final String ValidAuthor = "Patryk";
        private final int ValidPagina1 = 100;
        private final int ValidPagina2 = 500;

    /**
     * Deze testen gebruiken vooral met de op voorhand ingevoerde boeken van de database.
     * De verwijderfunctie staat in een aparte klasse TestClass_3_verwijder_functie om niet in de weg te lopen van deze testen.
     */


    @Before
        public void setUp() {

    		System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\2de semester\\web 2\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        @After
             public void clean() {
                 driver.quit();
        }

    /**
     * Gaat na of het laatst opgezocht (geldige) boek weergeeft.
     */
    @Test
    public void logboekTest(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Zoek")).click();
            driver.findElement(By.id("titel")).sendKeys("Witcher");
            driver.findElement(By.className("submit")).click();
            driver.findElement(By.linkText("Logboek")).click();
            WebElement check = driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[1]/th[2]"));
            assertEquals("Witcher", check.getText());
    }

    /**
     * Checkt of bij het vinden van een boek in de databank de client zich op de 'Reading club - Gevonden' bevindt.
     * Hier wordt Witcher gebruikt.
     */
    @Test
        public void search_valid_item_check_pagina_titel(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Zoek")).click();
            driver.findElement(By.id("titel")).sendKeys("Witcher");
            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Gevonden", driver.getTitle());
        }

    /**
     * Checkt of de juiste waardes worden terug gegeven bij het vinden van een boek in de database.
     * Hier wordt Witcher gebruikt.
     */
    @Test
    public void search_valid_item_check_output(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Zoek")).click();
        driver.findElement(By.id("titel")).sendKeys("Witcher");
        driver.findElement(By.className("submit")).click();
        WebElement check = driver.findElement(By.xpath("/html/body/main/section/p"));
        assertEquals("Titel: Witcher\n" +
                "Auteur: Sapkowski\n" +
                "Aantal pagina's: 286\n" +
                "score: 9", check.getText());

    }

    /**
     * Checkt of de client bij de pagina "Reading club - Niet gevonden" terecht komt indien de waarde die in de zoekopdracht
     * werd ingegeven zich niet in de database bevindt.
     */
    @Test
         public void search_invalid_item(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Zoek")).click();
            driver.findElement(By.id("titel")).sendKeys(" ");
            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Niet gevonden", driver.getTitle());
        }

    /**
     * De test probeert een nieuw boek aan te maken met lege waardes voor autheur, titel, en het aantal pagina's.
     * Bij de inzending van het formulier zou de client terug terecht komen op de pagina met in te vullen formulier.
     * Voor elke leeg veld zou een error moeten teruggegeven worden.
     */
    @Test
    public void voeg_leeg_item_toe(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Boek Toevoegen")).click();

            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Boek toevoegen", driver.getTitle());
            WebElement check = driver.findElement(By.xpath("/html/body/main/section/div/ul/li[1]"));
            assertEquals("Titel mag niet leeg zijn", check.getText());
            WebElement check1 = driver.findElement(By.xpath("/html/body/main/section/div/ul/li[2]"));
            assertEquals("Autheur mag niet leeg zijn", check1.getText());
            WebElement check2 = driver.findElement(By.xpath("/html/body/main/section/div/ul/li[3]"));
            assertEquals("Voer een getal voor pagina's in", check2.getText());
        }


    /**
     * Een formulier met geldiege gegevens wordt verstuurd om een nieuwe boek aan te maken.
     * Bij de inzending zou de client op het overzichts pagina moeten komen met daarin het toegevoegde item.
     * Het toegevoegde item wordt dan verwijderd om andere testen niet in de weg te liggen.
     */
    @Test
        public void voeg_valide_item_toe(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Boek Toevoegen")).click();
            driver.findElement(By.id("titel")).sendKeys(ValidTitle);
            driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
            driver.findElement(By.id("pagina")).sendKeys("100");
            driver.findElement(By.className("submit")).click();


            assertEquals("Reading club - Overzicht", driver.getTitle());
            WebElement check = driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[4]/td[1]"));
            assertEquals("Web", check.getText());
            driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[4]/td[6]/a")).click();
            driver.findElement(By.className("submit")).click();
        }

    /**
     * Een formulier met een lege titel wordt verzonden.
     * Een error voor titel zou teruggegeven moeten worden.
     */
    @Test
        public void voeg_leeg_titel_toe(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Boek Toevoegen")).click();
            driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
            driver.findElement(By.id("pagina")).sendKeys("100");
            driver.findElement(By.className("submit")).click();

            assertEquals("Reading club - Boek toevoegen", driver.getTitle());
            WebElement check = driver.findElement(By.className("alerts"));
            assertEquals("Titel mag niet leeg zijn", check.getText());
        }

    /**
     * Een formulier met een lege waarde voor autheur wordt verzonden.
     * Een error met voor de waarde auteur wordt teruggegeven.
     */
    @Test
    public void voeg_leeg_autheur_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys("oop");
        driver.findElement(By.id("pagina")).sendKeys("100");
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.className("alerts"));
        assertEquals("Autheur mag niet leeg zijn", check.getText());
    }

    /**
     * Een formulier met een lege waarde voor pagina's wordt verzonden.
     * Een error voor de waarde pagina's zou teruggegeven moeten worden.
     */
    @Test
    public void voeg_leeg_pagina_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys("oop");
        driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.className("alerts"));
        assertEquals("Voer een getal voor pagina's in", check.getText());
    }

    /**
     * Een formulier wordt verzonden met andere waardes dan getallen bij pagina's
     * Een error voor de waarde van pagina zou moeten teriggegeven worden.
     */
    @Test
    public void voeg_ongeldige_pagina_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys("oop");
        driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
        driver.findElement(By.id("pagina")).sendKeys("abc");
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.className("alerts"));
        assertEquals("Voer een getal voor pagina's in", check.getText());
    }

    /**
     * Een waarde wordt opgezocht in de zoek functie.
     * Daarna gaat de client terig naar de zoek pagina.
     * Een waarde die overeenkomt met de eerder ingevoerde waarde voor de zoekfunctie zou op de pagina moeten verschijnen.
     */
    @Test
    public void search_and_check_cookie(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Zoek")).click();
        driver.findElement(By.id("titel")).sendKeys("azert");
        driver.findElement(By.className("submit")).click();
        driver.findElement(By.linkText("Zoek")).click();
        WebElement check = driver.findElement(By.xpath("/html/body/div/p"));
        assertEquals("Je zocht laatst naar: azert", check.getText());
    }

    /**
     * Er wordt gecheckt of de client het gewenste boek update.
     */
    @Test
    public void update_pagina_juiste_item(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[2]/td[5]/a")).click();
        WebElement check = driver.findElement(By.xpath("/html/body/main/section/p"));
        assertEquals("Titel boek: Influence", check.getText());
    }

    /**
     * Er wordt gecheckt of een waarde van het boek Influence succesvol wordt aangepast.
     */
    @Test
    public void check_of_update_gelukt_is(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[2]/td[5]/a")).click();
        WebElement value = driver.findElement(By.id("pagina"));
        value.clear();
        value.sendKeys("999");
        driver.findElement(By.className("submit")).click();
        WebElement check = driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[2]/td[3]"));
        assertEquals("999", check.getText());
    }


    /**
     * Een nieuw boek wordt 2 keer toegevoegd.
     * Bij de tweede poging moet een error teruggegeven worden.
     * Het toegevoegde item wordt dan verwijderd om andere testen niet in de weg te liggen.
     */
    @Test
    public void voeg_dezelfde_item_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys("moonlight");
        driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
        driver.findElement(By.id("pagina")).sendKeys("100");
        driver.findElement(By.className("submit")).click();

        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys("moonlight");
        driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
        driver.findElement(By.id("pagina")).sendKeys("100");
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.xpath("/html/body/main/section/div/ul/li"));
        assertEquals("Dit boek bestaat al in de database", check.getText());

        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[4]/td[6]/a")).click();
        driver.findElement(By.className("submit")).click();
    }
}
