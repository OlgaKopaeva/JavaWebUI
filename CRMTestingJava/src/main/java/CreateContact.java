import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContact {

    private static WebDriver driver;
    private static WebDriverWait waitSevenSeconds;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitSevenSeconds = new WebDriverWait(driver, 5);
    }

    public static void main(String[] args) {
        login();

        driver.findElement(By.xpath(".//span[@class='title' and text()='Контрагенты']")).click();
        waitSevenSeconds.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//span[@class='title' and text()='Контактные лица']"))));
        driver.findElement(By.xpath(".//span[@class='title' and text()='Контактные лица']")).click();

        //Кнопка "Создать контактное лицо"
        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='container']//a[@title='Создать контактное лицо']")));
        driver.findElement(By.xpath(".//*[@id='container']//a[@title='Создать контактное лицо']")).click();

        //Поле "Фамилия"
        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@name='crm_contact[lastName]']")));
        driver.findElement(By.xpath(".//input[@name='crm_contact[lastName]']")).sendKeys("MyLastName");
        //Поле "Имя"
        driver.findElement(By.xpath(".//input[@name='crm_contact[firstName]']")).sendKeys("MyName");

        //Выпадающий список "Организация"
        driver.findElement(By.xpath(".//div[@class='select2-container select2 input-widget']/a")).click();
        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='select2-drop']/ul[@class='select2-results']/li[2]")));
        driver.findElement(By.xpath(".//div[@id='select2-drop']/ul[@class='select2-results']/li[2]")).click();

        //Поле "Должность"
        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("MyJob");

        //Кнопка "Сохранить"
        driver.findElement(By.xpath(".//button[@class='btn btn-success action-button']")).click();

    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput2']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@id='_submit']"));
        loginButton.click();
    }

}
