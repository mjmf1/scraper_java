import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebScraper {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mjmf\\Downloads\\scraper_java-master\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        try {
            String url = "https://www.ktronix.com/computadores-tablet/computadores-portatiles/c/BI_104_KTRON"; // Reemplaza con la URL real
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ais-InfiniteHits-item")));

            Document doc = Jsoup.parse(driver.getPageSource());

            Elements productItems = doc.select(".ais-InfiniteHits-item");

            for (Element productItem : productItems) {
                Element productTitleElement = productItem.select(".product_itemtop_title a").first();
                String productTitle = productTitleElement.text();
                String productCode = productTitleElement.attr("data-id");

                Element productPriceElement = productItem.select(".product_price--discounts_price span.price").first();
                String productPrice = productPriceElement.text().replace("$", "").replace(".", "");

                System.out.println("Nombre: " + productTitle);
                System.out.println("Precio: $" + productPrice);
                System.out.println("Código: " + productCode);
                System.out.println("--------------");
            }
        } finally {
            driver.quit();
        }
    }
}