import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.Extension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestProvider implements Extension {

    public static WebDriver driver;
    public static Cookie cookie;
    public static WebDriverWait waitSevenSeconds;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final String COOKIE_NAME = "BAPID";

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        waitSevenSeconds = new WebDriverWait(driver,7);
    }

    public static void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.manage().window().maximize();

        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput2']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@id='_submit']"));
        loginButton.click();
        cookie = driver.manage().getCookieNamed(COOKIE_NAME);
    }

}
