package ru.gb.webui.views;

import org.openqa.selenium.WebDriver;
import ru.gb.webui.base.BaseView;
import ru.gb.webui.base.SubMenu;
import ru.gb.webui.base.SubMenuButtons;
import ru.gb.webui.enums.ContactSubMenuButtons;
import ru.gb.webui.pages.AllContactsPage;

public class ContactsSubMenu extends SubMenu {

    public ContactsSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ContactSubMenuButtons) {
            switch ((ContactSubMenuButtons) buttons) {
                case CONTACTS:
                    driver.findElement(((ContactSubMenuButtons) buttons).getBy()).click();
                    return new AllContactsPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
