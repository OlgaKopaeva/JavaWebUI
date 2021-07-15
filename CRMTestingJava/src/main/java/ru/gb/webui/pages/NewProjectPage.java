package ru.gb.webui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.gb.webui.base.BaseView;

public class NewProjectPage extends BaseView {

    @FindBy(xpath = ".//input[@name='crm_project[name]']")
    private WebElement projectName;

    @FindBy(xpath = ".//div[@class='select2-container select2 input-widget']/a")
    private WebElement organizationDropDown;

    @FindBy(xpath = ".//div[@id='select2-drop']/div[@class='select2-search']/input")
    private WebElement organizationField;

    @FindBy(name = "crm_project[contactMain]")
    private WebElement contactMainDropDownSelect;

    @FindBy(name = "crm_project[businessUnit]")
    private WebElement businessUnitDropDownSelect;

    @FindBy(name = "crm_project[curator]")
    private WebElement curatorDropDownSelect;

    @FindBy(name = "crm_project[rp]")
    private WebElement rpDropDownSelect;

    @FindBy(name = "crm_project[administrator]")
    private WebElement adminDropDownSelect;

    @FindBy(name = "crm_project[manager]")
    private WebElement managerDropDownSelect;

    @FindBy(css = "button[class='btn btn-success action-button']")
    private WebElement submitButton;

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage setProjectName(String name) {
        projectName.clear();
        projectName.sendKeys(name);
        return this;
    }

    public NewProjectPage setOrganization(String organizationName) throws InterruptedException {
        organizationDropDown.click();
        Thread.sleep(5000);
        organizationField.sendKeys(organizationName);
        Thread.sleep(3000);
        organizationField.sendKeys(Keys.ENTER);
        return this;
    }

    public NewProjectPage selectContactMain(int value) {
        Select contactMainDropDown = new Select(contactMainDropDownSelect);
        contactMainDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectBusinessUnit(int value) {
        Select businessUnitDropDown = new Select(businessUnitDropDownSelect);
        businessUnitDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectCurator(int value) {
        Select curatorDropDown = new Select(curatorDropDownSelect);
        curatorDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectRp(int value) {
        Select rpDropDown = new Select(rpDropDownSelect);
        rpDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectAdmin(int value) {
        Select adminDropDown = new Select(adminDropDownSelect);
        adminDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectManager(int value) {
        Select managerDropDown = new Select(managerDropDownSelect);
        managerDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public AllProjectsPage clickSubmit() {
        submitButton.click();
        return new AllProjectsPage(driver);
    }
}
