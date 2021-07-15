package ru.gb.webui.pages;

import org.openqa.selenium.WebDriver;
import ru.gb.webui.base.BaseView;
import ru.gb.webui.views.NavigationBar;

public class HomePage extends BaseView {

    private NavigationBar navigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    public NavigationBar getPageNavigation() {
        return navigationBar;
    }
}
