package ru.gb.webui.views;

import org.openqa.selenium.WebDriver;
import ru.gb.webui.base.BaseView;
import ru.gb.webui.base.SubMenu;
import ru.gb.webui.base.SubMenuButtons;
import ru.gb.webui.enums.ProjectSubMenuButtons;
import ru.gb.webui.pages.AllProjectsPage;
import ru.gb.webui.pages.MyProjectsPage;

public class ProjectsSubMenu extends SubMenu {

    public ProjectsSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ProjectSubMenuButtons) {
            switch ((ProjectSubMenuButtons) buttons) {
                case ALL_PROJECTS:
                    driver.findElement(((ProjectSubMenuButtons) buttons).getBy()).click();
                    return new AllProjectsPage(driver);
                case MY_PROJECTS:
                    driver.findElement(((ProjectSubMenuButtons) buttons).getBy()).click();
                    return new MyProjectsPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
