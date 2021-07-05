import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class CreateProject {

    private static WebDriver driver;
    private static WebDriverWait waitSevenSeconds;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitSevenSeconds = new WebDriverWait(driver, 7);

    }

    public static void main(String[] args) throws InterruptedException {
        login();

        driver.findElement(By.xpath(".//span[@class='title' and text()='Проекты']")).click();

        waitSevenSeconds.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//span[@class='title' and text()='Все проекты']"))));
        driver.findElement(By.xpath(".//span[@class='title' and text()='Все проекты']")).click();

        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((".//div[@class='pull-left btn-group icons-holder']/a[text()='Создать проект']"))));
        driver.findElement(By.xpath(".//div[@class='pull-left btn-group icons-holder']/a[text()='Создать проект']")).click();

        //Поле "Наименование"
        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@name='crm_project[name]']")));
        driver.findElement(By.xpath(".//input[@name='crm_project[name]']")).sendKeys("MyProjectTest7");

        //Выпадающий список "Организация"
        driver.findElement(By.xpath(".//div[@class='select2-container select2 input-widget']/a")).click();
        waitSevenSeconds.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='select2-drop']/ul[@class='select2-results']/li[2]")));
        driver.findElement(By.xpath(".//div[@id='select2-drop']/ul[@class='select2-results']/li[2]")).click();

        //Выпадающий список "Основное контактное лицо"
        Select contactMainDropDown = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        contactMainDropDown.selectByValue("1");
        System.out.println("Select contactMain: " + contactMainDropDown.getFirstSelectedOption().getText());

        //Выбор поля "подразделение"
        Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        businessUnitDropDown.selectByValue("1");
        System.out.println("Select businessUnit: " + businessUnitDropDown.getFirstSelectedOption().getText());

        //Поле "Куратор проекта"
        Select curatorDropDown = new Select(driver.findElement(By.name("crm_project[curator]")));
        curatorDropDown.selectByValue("117");
        System.out.println("Select curator: " + curatorDropDown.getFirstSelectedOption().getText());

        //Поле "Руководитель проекта"
        Select rpDropDown = new Select(driver.findElement(By.name("crm_project[rp]")));
        rpDropDown.selectByValue("115");
        System.out.println("Select rp: " + rpDropDown.getFirstSelectedOption().getText());

        //Поле "Администратор проекта"
        Select adminDropDown = new Select(driver.findElement(By.name("crm_project[administrator]")));
        adminDropDown.selectByValue("116");
        System.out.println("Select admin: " + adminDropDown.getFirstSelectedOption().getText());

        //Поле "Менеджер"
        Select managerDropDown = new Select(driver.findElement(By.name("crm_project[manager]")));
        managerDropDown.selectByValue("117");
        System.out.println("Select manager: " + managerDropDown.getFirstSelectedOption().getText());

        //КНопка "сохранить и закрыть"
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



