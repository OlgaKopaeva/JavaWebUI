package ru.gb.webui.enums;

import org.openqa.selenium.By;
import ru.gb.webui.base.SubMenuButtons;

public enum ContactSubMenuButtons implements SubMenuButtons {
    ORGANIZATIONS(".//span[@class='title' and text()='Организации']"),
    PAYERS(".//span[@class='title' and text()='Плательщики']"),
    CONTACTS(".//span[@class='title' and text()='Контактные лица']");

    private final By by;

    ContactSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
