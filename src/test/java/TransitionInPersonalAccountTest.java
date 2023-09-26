import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import testData.client.UserActions;
import testData.client.userData.GenerateUserData;
import testData.getAndSet.CreateUserRequest;

import java.util.Objects;

public class TransitionInPersonalAccountTest {
    private WebDriver driver;
    private String accessToken;

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
    @Step("Перейти в личный кабинет через кнопку 'Личный кабинет', авторизовавшись под пользователем")
    public void testClickPersonalAccountWithAuth() {
        //создание пользователя
        CreateUserRequest createUserRequest = GenerateUserData.getRandomCreateUserRequest();
        accessToken = UserActions.create(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //логин
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.login(createUserRequest.getEmail(), createUserRequest.getPassword());

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickButtonPersonalAccount();

        AccountPage objPersonalAcc = new AccountPage(driver);
        objPersonalAcc.waitLoadPage();

        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/account/profile";
        Assert.assertEquals("Ошибка: пользователь НЕ находится в личном кабинете", expected, actual);
    }

    @Test
    @Step("Перейти в личный кабинет через кнопку 'Личный кабинет' под неавторизованным пользователем")
    public void testClickPersonalAccountWithoutAuth() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickButtonPersonalAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка: пользователь НЕ находится на странице входа", expected, actual);
    }
    @After
    public void teardown() {

        if( !(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, "")) ) {
            UserActions.delete(accessToken)
                    .statusCode(202);
        }

        //закрыть страницу
        driver.quit();
    }
}
