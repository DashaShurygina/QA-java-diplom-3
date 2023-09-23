import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.HomePage;
import pageObject.RecoveryPassword;
import pageObject.RegisterPage;


public class LoginTest {
 WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Darya Shurigina\\Downloads\\apache-maven-3.9.1-bin\\apache-maven-3.9.1\\bin\\WebDriver\\bin\\chromedriver.exe");
        ChromeDriverService service = new ChromeDriverService.Builder().build();
        this.driver = new ChromeDriver(service);
        this.driver = new ChromeDriver();

        /**драйвер для яндекс браузера
         System.setProperty("webdriver.chrome.driver","укажи_путь_к_драйверу");
         ChromeDriverService service = new ChromeDriverService.Builder().build();
         this.driver = new ChromeDriver(service);*/

    }


    @Test
    @Step("Кликнуть по кнопке 'Войти' на странице восстановления пароля")
    public void clickSignInFromForgotPassPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        RecoveryPassword objForgotPassPage = new RecoveryPassword(driver);
        objForgotPassPage.clickButtonSignIn();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из страницы восстановления пароля на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть по кнопке 'Войти в аккаунт' на главной странице")
    public void clickSignInFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickButtonSignInAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из стартовой страницы на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть по кнопке 'Личный кабинет' из главной страницы")
    public void clickPersonalAccountFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickButtonPersonalAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из стартовой страницы на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть по кнопке 'Войти' на странице регистрации пользователя")
    public void clickSignInFromRegisterPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.clickButtonSignIn();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из страницы регистрации на страницу входа в систему", expected, actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}