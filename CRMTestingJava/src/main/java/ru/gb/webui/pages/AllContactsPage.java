package ru.gb.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.gb.webui.base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllContactsPage extends BaseView {

    @FindBy(xpath = ".//*[@id='container']//a[@title='Создать контактное лицо']")
    private WebElement createNewContactButton;

    public AllContactsPage(WebDriver driver) {
        super(driver);
    }

    public NewContactPage clickOnCreateNewContactButton() {
        createNewContactButton.click();
        return new NewContactPage(driver);
    }

    @Step("Check alert text 'New contact is created'")
    public AllContactsPage checkNewContactPopUp() {
        String message = wait7seconds.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "div[class='message']"))).getText();
        assertTrue(message.contains("Контактное лицо сохранено"));
        return this;
    }
}
