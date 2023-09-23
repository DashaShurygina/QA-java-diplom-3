package registrationTest;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.RegisterPage;

public class InvalidPasswordTest {
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

    }


    @Test
    @Step("Проверка: Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    public void checkErrorWhenEnteringPasswordLessThanSixCharacters() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.register("test", "tatmel@yandex.ru", "12345");
        String actual = objRegisterPage.findTextIncorrectPassword();
        Assert.assertEquals( "Некорректный пароль", actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}