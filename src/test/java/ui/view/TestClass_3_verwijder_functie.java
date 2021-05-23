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
 * Deze Testen als laatste uitvoeren!!
 * Deze test verwijderd het boek Witcher dat gebruikt wordt in om verschillende functies van de site te testen.
 * Na het uitvoeren van deze test moet de server herstart worden zodat de andere testen kunnen slagen aangezien het
 * eerste boek uit de lijst verwijderd is.
 */

public class TestClass_3_verwijder_functie {

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


    // verwijderd het eerste boek in de databank (Witcher) en checkt de titel van het volgende boek in de databank
    //Influence is dat zich oorsponkelijk in de eerste plaats bevond.
    @Test
    public void verwijder_element(){
        driver.get("http://localhost:8080/Piekarz_Patryk_war_exploded/");
        driver.findElement(By.linkText("Overzicht")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[1]/td[6]/a")).click();
        driver.findElement(By.xpath("/html/body/main/section/form/input")).click();
        assertEquals("Reading club - Overzicht", driver.getTitle());
        String check = driver.findElement(By.xpath("/html/body/main/section/div/table/tbody/tr[1]/td[1]")).getText();
        System.out.println(check);
        assertEquals("Influence", check);
    }
}
