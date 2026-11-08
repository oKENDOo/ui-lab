package uilab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final String URL = "https://quickpizza.grafana.com/";
    private static final By HEADING = By.tagName("h1");
    private static final By ADVANCED = By.xpath("//label[.//span[normalize-space()='Advanced']]");
    private static final By MAX_CALORIES = By.xpath("//label[normalize-space()='Max Calories per Slice']/preceding-sibling::input");
    private static final By TOOLS = By.tagName("select");
    private static final By VEGETARIAN = By.id("default-checkbox");
    private static final By PIZZA_NAME = By.cssSelector("input#pizza-name");
    private static final By PIZZA_PLEASE = By.name("pizza-please");
    private static final By RESULT = By.id("recommendations");
    private static final By LOVE_IT = By.name("rate-5");
    private static final By RATE_RESULT = By.id("rate-result");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) { this.driver = driver; this.wait = wait; }
    public void open() { driver.get(URL); wait.until(ExpectedConditions.visibilityOfElementLocated(HEADING)); }
    public String heading() { return driver.findElement(HEADING).getText(); }
    public void showAdvanced() { wait.until(ExpectedConditions.elementToBeClickable(ADVANCED)).click(); wait.until(ExpectedConditions.visibilityOfElementLocated(MAX_CALORIES)); }
    public void setMaxCalories(int calories) { WebElement box = driver.findElement(MAX_CALORIES); box.clear(); box.sendKeys(String.valueOf(calories)); }
    public String maxCalories() { return driver.findElement(MAX_CALORIES).getDomProperty("value"); }
    public void excludeTool(String tool) { By option = By.xpath("//select/option[normalize-space()='" + tool + "']"); wait.until(ExpectedConditions.presenceOfElementLocated(option)); new Select(driver.findElement(TOOLS)).selectByVisibleText(tool); }
    public void mustBeVegetarian() { driver.findElement(VEGETARIAN).click(); }
    public void nameThePizza(String name) { driver.findElement(PIZZA_NAME).sendKeys(name); }
    public void askForPizza() { driver.findElement(PIZZA_PLEASE).click(); wait.until(ExpectedConditions.textToBePresentInElementLocated(RESULT, "Calories per slice:")); }
    public String result(String label) { By line = By.xpath("//div[@id='recommendations']//p[starts-with(normalize-space(), '" + label + "')]"); return driver.findElement(line).getText().substring(label.length()).trim(); }
    public String loveIt() { driver.findElement(LOVE_IT).click(); return wait.until(ExpectedConditions.visibilityOfElementLocated(RATE_RESULT)).getText(); }
}
