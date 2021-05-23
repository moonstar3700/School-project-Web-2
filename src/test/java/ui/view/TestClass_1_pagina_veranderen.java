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

/**
 * eerste Deze testen laten runnen.
 */



public class TestClass_1_pagina_veranderen {

    private WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\2de semester\\web 2\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void Ga_naar_hoofdpagina() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Home")).click();
        assertEquals("Reading club", driver.getTitle());
    }

    @Test
    public void Ga_naar_BoekToevoegen() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Boek Toevoegen")).click();
        assertEquals("Reading club - Boek toevoegen", driver.getTitle());
    }

    @Test
    public void Ga_naar_Overzicht() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        assertEquals("Reading club - Overzicht", driver.getTitle());
    }

    @Test
    public void Ga_naar_DeleteConfirmation() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[1]/td[6]/a")).click();
        assertEquals("Reading club - Bevestiging", driver.getTitle());
    }

    @Test
    public void Ga_naar_Updaten() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[1]/td[5]/a")).click();
        assertEquals("Reading club - Boek updaten", driver.getTitle());
    }

    @Test
    public void Ga_naar_Zoek() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Zoek")).click();
        assertEquals("Reading club - Zoek", driver.getTitle());
    }

    @Test
    public void Ga_naar_Logboek() {
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Logboek")).click();
        assertEquals("Reading club - logboek", driver.getTitle());
    }


}
