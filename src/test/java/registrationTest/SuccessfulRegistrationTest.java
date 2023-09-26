package registrationTest;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.RegisterPage;
import testData.client.UserActions;
import testData.client.userData.GenerateUserData;
import testData.getAndSet.CreateUserRequest;
import testData.getAndSet.LoginUserRequest;

import java.util.Objects;
import java.util.Random;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTest {
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

    private final String name;
    private final String email;
    private final String password;

    public SuccessfulRegistrationTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Имя, email и пароль для регистрации пользователя. Тестовые данные: {0} , {1} , {2}")
    public static Object[][] sendFieldsForSuccessfulRegistration() {
        Random random = new Random();
        return new Object[][]{
                {"Dasha151613", "dasha" + random.nextInt(100000000) +"@yandex.ru", "151613"},
                {"Дарья", "dasha" + random.nextInt(100000000) + "@gmail.com", "1237456"},
                {"D", random.nextInt(100000000) + "@mail.ru", "1234567"},
                {"Qwertyuiopasdfghjklzxcvbnm", "dashaD" + random.nextInt(100000000) + "@ya.ru", "12348567"},
                {"Да ша", random.nextInt(100000000) + "@ya.ru", "123 852"}
        };
    }

    @Test
    @Step("Успешная регистрация")
    public void checkSuccessfulRegistration() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.register(name, email, password);
        driver.get("https://stellarburgers.nomoreparties.site/register");
        objRegisterPage.register(name, email, password);
        String actual = objRegisterPage.findTextUserAlreadyRegistered();
        Assert.assertEquals("Такой пользователь уже существует",actual);
    }

    @After
    public void teardown() {

        CreateUserRequest createUserRequest = GenerateUserData.getDataCreatedUser(email, password, name);
        LoginUserRequest loginUserRequest = LoginUserRequest.from(createUserRequest);
        accessToken = UserActions.login(loginUserRequest)
                .statusCode(200)
                .extract().jsonPath().get("accessToken");

        if( !(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, "")) ) {
            UserActions.delete(accessToken)
                    .statusCode(202);
        }

        //закрыть страницу
        driver.quit();
    }
}