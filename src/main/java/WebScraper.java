import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WebScraper {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        try {
            String url = "https://www.ktronix.com/computadores-tablet/computadores-portatiles/c/BI_104_KTRON";
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ais-InfiniteHits-item")));

            Document doc = Jsoup.parse(driver.getPageSource());

            // Establecer la conexión a la base de datos MySQL
            String dbUrl = "jdbc:mysql://localhost:3306/datos";
            String username = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Conexión a la base de datos MySQL establecida.");

            Elements productItems = doc.select(".ais-InfiniteHits-item");

            // Limitar el bucle para procesar solo 5 productos
            int productsToScrape = 5;
            for (int i = 0; i < Math.min(productsToScrape, productItems.size()); i++) {
                Element productItem = productItems.get(i);

                Element productTitleElement = productItem.select(".product__item__top__title a").first();
                String productName = productTitleElement.text();
                String productCode = productTitleElement.attr("data-id");

                Element productPriceElement = productItem.select(".product__price--discounts__price span.price").first();
                String productPrice = productPriceElement.text().replace("$", "").replace(".", "").replace(",", "");

                // Insertar los datos en la base de datos
                String insertQuery = "INSERT INTO productos (nombre, precio, codigo) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, productName);
                preparedStatement.setDouble(2, Double.parseDouble(productPrice));
                preparedStatement.setString(3, productCode);
                preparedStatement.executeUpdate();

                System.out.println("Nombre: " + productName);
                System.out.println("Precio: $" + productPrice);
                System.out.println("Código: " + productCode);
                System.out.println("--------------");
            }
            // Cerrar la conexión a la base de datos
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
