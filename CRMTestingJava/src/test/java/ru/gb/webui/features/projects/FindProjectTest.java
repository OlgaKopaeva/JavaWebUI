package ru.gb.webui.features.projects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.gb.webui.base.BaseUITest;
import ru.gb.webui.common.Configuration;
import ru.gb.webui.enums.NavigationBarTabs;
import ru.gb.webui.enums.ProjectSubMenuButtons;
import ru.gb.webui.pages.LoginPage;
import ru.gb.webui.pages.MyProjectsPage;

public class FindProjectTest extends BaseUITest {

    @Test
    @DisplayName("Выбор проекта с заданным именем")
    public void findProjectPositiveTest() {
        MyProjectsPage myProjectsScreen = (MyProjectsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                .clickSubMenuButton(ProjectSubMenuButtons.MY_PROJECTS);

        myProjectsScreen.setMyProjectName("MyProject");

        String foundedProjectNameXPath = ".//td[@class='string-cell grid-cell grid-body-cell grid-body-cell-name' and text()='MyProject']";
        Assertions.assertTrue(driver.findElement(By.xpath(foundedProjectNameXPath)).isDisplayed());
    }
}
