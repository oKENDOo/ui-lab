package uilab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    private static final String URL = "https://quickpizza.grafana.com/admin";
    private static final By READY = By.id("svelte-announcer");
    private static final By USER = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By SIGN_IN = By.cssSelector("button[type=submit]");
    private final WebDriver driver;
    private final WebDriverWait wait;
    public AdminPage(WebDriver driver, WebDriverWait wait) { this.driver = driver; this.wait = wait; }
    public void open() { driver.get(URL); wait.until(ExpectedConditions.presenceOfElementLocated(READY)); }
    public void logIn(String user, String password) { replace(USER, user); replace(PASSWORD, password); driver.findElement(SIGN_IN).click(); wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Latest pizza recommendations")); }
    public String lineFor(String pizzaName) { By line = By.xpath("//li[contains(., '" + pizzaName + "')]"); return wait.until(ExpectedConditions.visibilityOfElementLocated(line)).getText(); }
    private void replace(By field, String text) { WebElement element = driver.findElement(field); element.clear(); element.sendKeys(text); }
}
