package ru.gb.webui.features.projects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gb.webui.base.BaseUITest;
import ru.gb.webui.common.Configuration;
import ru.gb.webui.enums.NavigationBarTabs;
import ru.gb.webui.enums.ProjectSubMenuButtons;
import ru.gb.webui.pages.AllProjectsPage;
import ru.gb.webui.pages.LoginPage;

import java.util.UUID;

public class NewProjectTest extends BaseUITest {

    @Test
    @DisplayName("Создание проекта с корректными параметрами")
    public void createNewProjectPositiveTest() throws InterruptedException {
        AllProjectsPage projectsScreen = (AllProjectsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                .clickSubMenuButton(ProjectSubMenuButtons.ALL_PROJECTS);

        String randomProjectName = UUID.randomUUID().toString();

        projectsScreen
                .clickOnCreateNewProjectButton()
                .setProjectName(randomProjectName)
                .setOrganization("Bins-Haley")
                .selectContactMain(1885)
                .selectBusinessUnit(1)
                .selectCurator(117)
                .selectRp(115)
                .selectAdmin(116)
                .selectManager(117)
                .clickSubmit()
                .checkNewProjectPopUp();
    }
}
