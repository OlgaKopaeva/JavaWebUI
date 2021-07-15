package ru.gb.webui.enums;

import org.openqa.selenium.By;
import ru.gb.webui.base.SubMenuButtons;

public enum ProjectSubMenuButtons implements SubMenuButtons {

    ALL_PROJECTS(".//span[@class='title' and text()='Все проекты']"),
    MY_PROJECTS(".//span[@class='title' and text()='Мои проекты']");

    private final By by;

    ProjectSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
