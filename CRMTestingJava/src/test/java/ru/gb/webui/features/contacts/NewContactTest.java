package ru.gb.webui.features.contacts;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gb.webui.base.BaseUITest;
import ru.gb.webui.common.Configuration;
import ru.gb.webui.enums.ContactSubMenuButtons;
import ru.gb.webui.enums.NavigationBarTabs;
import ru.gb.webui.pages.AllContactsPage;
import ru.gb.webui.pages.LoginPage;

public class NewContactTest extends BaseUITest {

    @Test
    @DisplayName("Создание контактного лица с корректными параметрами")
    public void createNewContactPositiveTest() throws InterruptedException {
        AllContactsPage contactsScreen = (AllContactsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.COUNTER_PARTIES)
                .clickSubMenuButton(ContactSubMenuButtons.CONTACTS);

        String randomLastName = RandomStringUtils.randomAlphabetic(5);
        String randomFirstName = RandomStringUtils.randomAlphabetic(4);

        contactsScreen
                .clickOnCreateNewContactButton()
                .setLastName(randomLastName)
                .setFirstName(randomFirstName)
                .setOrganization("Bins-Haley")
                .setJobTitle("MyJob")
                .clickSubmit()
                .checkNewContactPopUp();
    }
}
