package ru.gb.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.gb.webui.base.BaseView;

public class MyProjectsPage extends BaseView {

    @FindBy(xpath = ".//div[@class='btn filter-criteria-selector oro-drop-opener oro-dropdown-toggle filter-default-value' and contains(text(), 'Наименование')]")
    private WebElement projectNameFilter;

    @FindBy(xpath = ".//input[@name='value']")
    private  WebElement projectNameField;

    @FindBy(xpath = ".//button[@type='button' and text()='Обновить']")
    private WebElement updateButton;

    @Step("Set project name {name}")
    public MyProjectsPage setMyProjectName(String name) {
        projectNameFilter.click();
        projectNameField.clear();
        projectNameField.sendKeys(name);
        updateButton.click();
        return this;
    }

    public MyProjectsPage(WebDriver driver) {
        super(driver);
    }
}
