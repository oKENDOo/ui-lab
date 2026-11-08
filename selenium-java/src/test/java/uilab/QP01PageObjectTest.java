package uilab;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import uilab.pages.AdminPage;
import uilab.pages.HomePage;

class QP01PageObjectTest extends BaseTest {
    @Test
    void customPizzaReachesTheAdminList() {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String pizzaName = "Lab Pizza " + stamp;
        HomePage home = new HomePage(driver, wait);
        AdminPage admin = new AdminPage(driver, wait);
        home.open();
        assertEquals("Looking to break out of your pizza routine?", home.heading());
        home.showAdvanced();
        home.setMaxCalories(600);
        assertEquals("600", home.maxCalories());
        home.excludeTool("Knife"); home.mustBeVegetarian(); home.nameThePizza(pizzaName); shot(stamp + "/step-3");
        home.askForPizza();
        assertEquals(pizzaName, home.result("Name: "));
        String tool = home.result("Tool: "); assertNotEquals("Knife", tool);
        int perSlice = Integer.parseInt(home.result("Calories per slice: "));
        assertTrue(perSlice <= 600, "calories per slice: " + perSlice); shot(stamp + "/step-4");
        assertEquals("Please log in first.", home.loveIt()); shot(stamp + "/step-5");
        admin.open(); admin.logIn("admin", "admin");
        String line = admin.lineFor(pizzaName); assertTrue(line.contains("(tool=" + tool + ","), line); shot(stamp + "/step-7");
    }
}
