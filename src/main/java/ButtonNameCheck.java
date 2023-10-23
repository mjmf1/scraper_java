import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ButtonNameCheck {
    public static void main(String[] args) {
        System.out.println("Ejecutando app.");
        // Specify the path to your ChromeDriver executable
        System.setProperty("webdriver.gecko.driver", "geckodriver");

        // Initialize the Chrome WebDriver
        WebDriver driver = new FirefoxDriver();


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Browserstack Guide"); //name locator for text box
        WebElement searchbutton = driver.findElement(By.name("btnK"));//name locator for google search
        searchbutton.click();
        driver.quit();
    }
}