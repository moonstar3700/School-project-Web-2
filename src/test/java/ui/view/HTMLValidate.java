package ui.view;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HTMLValidate {
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
    public void isValidHtmlhome() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlBoekToevoegen() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?command=BoekToevoegen");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }

    /**
     * Deze test zal enkel werken indien er geen waardes in dedatabase zijn met een spatie in de string.
     */
    @Test
    public void isValidHtmlOverzicht() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?command=Overzicht");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlUpdateWitcher() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?command=updatePage&titel=Witcher&autheur=Andrzej%20Sapkowski&pagina=286&score=9");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlVerwijderWitcherBevestiging() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?command=deleteConfirmation&titel=Witcher&autheur=Andrzej%20Sapkowski");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }

    @Test
    public void isValidHtmlZoek() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?command=searchPage");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlNietGevonden() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?titel=sdf&command=search");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlWitcherGevonden() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/BoekForm?titel=Witcher&command=search");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }
    @Test
    public void isValidHtmlLogboek() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Piekarz-Patryk-1.0-SNAPSHOT/Logboek.jsp");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.cssSelector(".submit_button"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());
    }

}