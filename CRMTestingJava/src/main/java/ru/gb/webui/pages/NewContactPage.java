package ru.gb.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.gb.webui.base.BaseView;

public class NewContactPage extends BaseView {

    @FindBy(name = "crm_contact[lastName]")
    private WebElement lastName;

    @FindBy(name = "crm_contact[firstName]")
    private WebElement firstName;

    @FindBy(xpath = ".//div[@class='select2-container select2 input-widget']/a")
    private WebElement organizationDropDown;

    @FindBy(xpath = ".//div[@class='select2-search']/input")
    private WebElement organizationField;

    @FindBy(name = "crm_contact[jobTitle]")
    private WebElement jobTitle;

    @FindBy(css = "button[class='btn btn-success action-button']")
    private WebElement submitButton;

    public NewContactPage(WebDriver driver) {
        super(driver);
    }

    @Step("Set last name {name}")
    public NewContactPage setLastName(String name) {
        lastName.clear();
        lastName.sendKeys(name);
        return this;
    }

    @Step("Set first name {name}")
    public NewContactPage setFirstName(String name) {
        firstName.clear();
        firstName.sendKeys(name);
        return this;
    }

    @Step("Set organization with name {organizationName}")
    public NewContactPage setOrganization(String organizationName) throws InterruptedException {
        organizationDropDown.click();
        Thread.sleep(5000);
        organizationField.sendKeys(organizationName);
        Thread.sleep(3000);
        organizationField.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Set job title {job}")
    public NewContactPage setJobTitle(String job) {
        jobTitle.clear();
        jobTitle.sendKeys(job);
        return this;
    }

    public AllContactsPage clickSubmit() {
        submitButton.click();
        return new AllContactsPage(driver);
    }
}
