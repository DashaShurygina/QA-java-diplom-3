package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;

    //локатор поле Имя
    private By fieldName = By.xpath(".//fieldset[1]//div/input");
    //локатор поле Email
    private By fieldEmail = By.xpath(".//fieldset[2]//div/input");
    //локатор поле Пароль
    private By fieldPassword = By.xpath(".//div/input[@name = 'Пароль']");
    //локатор кнопки "Зарегистрироваться"
    private By buttonRegister = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //локатор ссылки Войти
    private By buttonSignIn = By.xpath(".//p/a[text() = 'Войти']");
    private By userAlreadyRegistered = By.xpath(".//p[text() = 'Такой пользователь уже существует']");
    private By incorrectPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для ввода значения в поле Имя
    public void setFieldName(String name) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
    }
    //Метод для ввода значения в поле Email
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }
    //Метод для ввода значения в поле Пароль
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }
    //Метод для клика по кнопке "Зарегистрироваться"
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }
    //Метод для клика по кнопке "Войти"
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }

    //Метод ожидания загрузки страницы
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRegister));
    }

    //Метод регистрации пользователя
    public void register(String name, String email, String password) {
        waitLoadPage();
        setFieldName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        clickButtonRegister();
    }
    //Метод ожидания появления текста о существовании пользователя при повторной регистрации
    public void waitTextUserAlreadyRegistered() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(userAlreadyRegistered));
    }

    //Метод для поиска текста "Такой пользователь уже существует" при повторной регистрации такого же пользователя
    public String findTextUserAlreadyRegistered() {
        waitTextUserAlreadyRegistered();
        return driver.findElement(userAlreadyRegistered).getText();
    }
    //Метод для поиска текста "Некорректный пароль" при вводе пароля менее 6 символов
    public String findTextIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }

}
