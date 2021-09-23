package ru.gb.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.gb.webui.base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllProjectsPage extends BaseView {
    @FindBy(xpath = ".//div[@class='pull-left btn-group icons-holder']/a[text()='Создать проект']")
    private WebElement createNewProjectButton;

    public AllProjectsPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage clickOnCreateNewProjectButton() {
        createNewProjectButton.click();
        return new NewProjectPage(driver);
    }

    @Step("Check alert text 'New project is created'")
    public AllProjectsPage checkNewProjectPopUp() {
        String message = wait7seconds.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "div[class='message']"))).getText();
        assertTrue(message.contains("Проект сохранен"));
        return this;
    }
}
