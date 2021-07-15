package ru.gb.webui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseView {

    protected WebDriver driver;
    protected WebDriverWait wait7seconds;

    public BaseView(WebDriver driver) {
        this.driver = driver;
        this.wait7seconds = new WebDriverWait(driver, 7);
        PageFactory.initElements(driver, this);
    }
}
