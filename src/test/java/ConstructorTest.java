import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.HomePage;


public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Darya Shurigina\\Downloads\\apache-maven-3.9.1-bin\\apache-maven-3.9.1\\bin\\WebDriver\\bin\\chromedriver.exe");
        ChromeDriverService service = new ChromeDriverService.Builder().build();
        this.driver = new ChromeDriver(service);
        this.driver = new ChromeDriver();

        /**драйвер для яндекс браузера
         System.setProperty("webdriver.chrome.driver","укажи_путь_к_драйверу");
         ChromeDriverService service = new ChromeDriverService.Builder().build();
         this.driver = new ChromeDriver(service);
         */

        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Step("Перейти в раздел 'Булки' (конструктор)")
    public void testTransitionInSectionBuns() {

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickSectionFilling();
        objHomePage.clickSectionBuns();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Булки']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue( actual);

    }

    @Test
    @Step("Перейти в раздел 'Соусы' (конструктор)")
    public void testTransitionInSectionSauce() {

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickSectionSauce();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Соусы']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue( actual);
    }

    @Test
    @Step("Перейти в раздел 'Начинки' (конструктор)")
    public void testTransitionInSectionFilling() {

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickSectionSauce();
        objHomePage.clickSectionFilling();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Начинки']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue( actual);
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
