import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@ExtendWith(TestProvider.class)
public class ContactList {
    WebDriver driver = TestProvider.driver;
    WebDriverWait waitSevenSeconds = TestProvider.waitSevenSeconds;
    private static final String MAIN_PAGE_URL = "https://crm.geekbrains.space/";
    private static final String CONTACT_PAGE_URL = "https://crm.geekbrains.space/contact/";
    public Cookie cookie = TestProvider.cookie;

    @BeforeAll
    static void beforeAllTests() {
        TestProvider.login();
    }

    @BeforeEach
    void beforeEachTest(){
        driver.get(MAIN_PAGE_URL);
        driver.manage().addCookie(cookie);
        driver.manage().window().maximize();
    }

    @AfterEach
    void afterEachTest(){
        driver.quit();
    }

    @Test
    @DisplayName("Создание контактного лица с корректными параметрами")
    public void createContact() {
        TestProvider.driver.findElement(By.xpath(".//span[@class='title' and text()='Контрагенты']")).click();
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

        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[@class='oro-subtitle']")));
        Assertions.assertEquals(driver.getCurrentUrl(),CONTACT_PAGE_URL);

    }


}
