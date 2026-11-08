package uilab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

class WebFormTest extends BaseTest {

    static final String FORM =
            "https://www.selenium.dev/selenium/web/web-form.html";

    // Goal: every kind of field takes input, and the form is received.
    @Test
    void fillAndSubmitTheForm() {
        driver.get(FORM);

        // text boxes
        WebElement text = driver.findElement(By.id("my-text-id"));
        text.sendKeys("Hello");
        assertEquals("Hello", text.getDomProperty("value"));
        driver.findElement(By.name("my-password")).sendKeys("Secret-26");
        driver.findElement(By.name("my-textarea")).sendKeys("Two lines");
        assertFalse(driver.findElement(By.name("my-disabled")).isEnabled());

        // drop-down lists and a file
        Select dropdown =
                new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByVisibleText("Two");
        assertEquals("2", dropdown.getFirstSelectedOption()
                .getDomProperty("value"));
        driver.findElement(By.name("my-datalist")).sendKeys("Seattle");
        String file = new File("pom.xml").getAbsolutePath();
        driver.findElement(By.name("my-file")).sendKeys(file);

        // Add the next part above this line.
    }
}
