package ru.gb.webui.views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.gb.webui.base.BaseView;
import ru.gb.webui.base.SubMenu;
import ru.gb.webui.enums.NavigationBarTabs;

public class NavigationBar extends BaseView {

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public SubMenu moveCursorToNavigationTab(NavigationBarTabs tab) {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(tab.getBy()))
                .build()
                .perform();
        switch (tab) {
            case PROJECTS:
                return new ProjectsSubMenu(driver);
            case COUNTER_PARTIES:
                return new ContactsSubMenu(driver);
            default:
                throw new IllegalArgumentException("Another tabs currently not implemented");
        }
    }

}
