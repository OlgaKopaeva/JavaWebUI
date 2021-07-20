package ru.gb.webui.features.projects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gb.webui.base.BaseUITest;
import ru.gb.webui.common.Configuration;
import ru.gb.webui.enums.NavigationBarTabs;
import ru.gb.webui.enums.ProjectSubMenuButtons;
import ru.gb.webui.pages.AllProjectsPage;
import ru.gb.webui.pages.LoginPage;

import java.util.UUID;

@Feature("Create new project")
@Severity(SeverityLevel.CRITICAL)
public class NewProjectTest extends BaseUITest {

    @Test
    @DisplayName("Create project with correct parameters")
    @Description("Создание проекта с корректными параметрами")
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
                .setOrganization(Configuration.ORGANIZATION)
                .selectContactMain(Configuration.VALUE_CONTACT_MAIN)
                .selectBusinessUnit(Configuration.VALUE_BUSINESS_UNIT)
                .selectCurator(Configuration.VALUE_CURATOR)
                .selectRp(Configuration.VALUE_RP)
                .selectAdmin(Configuration.VALUE_RP)
                .selectManager(Configuration.VALUE_MANAGER)
                .clickSubmit()
                .checkNewProjectPopUp();
    }
}
