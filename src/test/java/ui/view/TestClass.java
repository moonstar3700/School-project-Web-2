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

public class TestClass {

        private WebDriver driver;
        private String ValidTitle = "Web";
        private String ValidAuthor = "Patryk";
        private int ValidPagina1 = 100;
        private int ValidPagina2 = 500;

        @Before
        public void setUp() {
            // vul hier JOUW pad naar chromedriver in
            // Voor Windows (vergeet "\" niet te escapen met een tweede "\")
    		System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\2de semester\\web 2\\chromedriver\\chromedriver.exe");
            // Voor mac:
            //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        }
        /*@After
             public void clean() {
                 driver.quit();
        }*/
        @Test
        public void search_valid_item(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Search")).click();
            driver.findElement(By.id("titel")).sendKeys("Witcher");
            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Gevonden", driver.getTitle());
        }

        @Test
         public void search_invalid_item(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Search")).click();
            driver.findElement(By.id("titel")).sendKeys(" ");
            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Niet gevonden", driver.getTitle());
        }

        @Test
        public void voeg_leeg_item_toe(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Boek Toevoegen")).click();
            driver.findElement(By.className("submit")).click();
            assertEquals("Reading club - Boek toevoegen", driver.getTitle());
            WebElement check = driver.findElement(By.className("alerts"));
            assertEquals("Titel mag niet leeg zijn", check.getText());
            WebElement check1 = driver.findElement(By.className("alerts"));
            assertEquals("Autheur mag niet leeg zijn", check1.getText());
            WebElement check2 = driver.findElement(By.className("alerts"));
            assertEquals("Voer een getal voor pagina's in", check2.getText());
        }

        @Test
        public void voeg_valide_item_toe(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Boek Toevoegen")).click();
            driver.findElement(By.id("titel")).sendKeys(ValidTitle);
            driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
            driver.findElement(By.id("pagina")).sendKeys("100");
            driver.findElement(By.className("submit")).click();


            assertEquals("Reading club - Overzicht", driver.getTitle());
            WebElement check = driver.findElement(By.xpath("//*[text()='Patryk']"));
            assertEquals("Patryk", check.getText());
        }

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

    @Test
    public void voeg_leeg_autheur_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys(ValidTitle);
        driver.findElement(By.id("pagina")).sendKeys("100");
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.className("alerts"));
        assertEquals("Autheur mag niet leeg zijn", check.getText());
    }

    @Test
    public void voeg_leeg_pagina_toe(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        driver.findElement(By.id("titel")).sendKeys(ValidTitle);
        driver.findElement(By.id("autheur")).sendKeys(ValidAuthor);
        driver.findElement(By.className("submit")).click();

        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
        WebElement check = driver.findElement(By.className("alerts"));
        assertEquals("Voer een getal voor pagina's in", check.getText());
    }

        @Test
        public void verwijder_element(){
            driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
            driver.findElement(By.linkText("Overzicht")).click();
            driver.findElement(By.xpath("//table/tbody/tr[0]/td[5]")).click();
            driver.findElement(By.id("submit")).click();
            assertEquals("Reading club - Overzicht", driver.getTitle());
            WebElement check = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
            assertEquals("Influence", check);
        }


        /*@Test // Voer deze test uit als je je project opgeladen hebt
        public void isValidHtml() {
            driver.get("https://validator.w3.org/#validate_by_uri+with_options");
            WebElement invulveld = driver.findElement(By.id("uri"));
            // verander naar de url die je wil valideren
            invulveld.sendKeys("https://webontwerp.ucll.be/web1/reeksen/");

            Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
            dropdown.selectByValue("HTML5");

            WebElement button = driver.findElement(By.cssSelector(".submit_button"));
            button.click();

            WebElement pass = driver.findElement(By.cssSelector("p.success"));
            assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());

        }*/

}
