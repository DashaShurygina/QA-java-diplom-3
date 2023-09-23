import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import testData.getAndSet.CreateUserRequest;
import testData.client.userData.GenerateUserData;
import testData.client.UserActions;

import java.util.Objects;

public class LogoutTest {
    private WebDriver driver;
    private UserActions userActions = new UserActions();
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

        CreateUserRequest createUserRequest = GenerateUserData.getRandomCreateUserRequest();
        accessToken = UserActions.create(createUserRequest)
                .extract().jsonPath().get("accessToken");

        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.login(createUserRequest.getEmail(), createUserRequest.getPassword());
    }

    @Test
    @Step("Выйти из аккаунта")
    public void testLogoutPersonalAccount() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickButtonPersonalAccount();

        AccountPage objAccountPage = new AccountPage(driver);
        objAccountPage.clickButtonExit();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitLoadPage();

        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals(expected, actual);
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
