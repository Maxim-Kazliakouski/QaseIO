package steps;

import dto.NewProject;
import factories.NewProjectFactory;
import lombok.extern.log4j.Log4j2;
import pages.NewProjectPage;
import pages.ProjectsPage;

import static java.lang.String.format;

@Log4j2
public class NewProjectPageSteps {

    NewProjectPage newProjectPage;
    ProjectsPage projectsPage;

    public NewProjectPageSteps() {
        newProjectPage = new NewProjectPage();
        projectsPage = new ProjectsPage();
    }

    public void createNewProject(String projectName) {
        NewProject newProject = NewProjectFactory.getByAccessType(projectName, "Public");
        log.info(format("Creating new project '%s'...", newProject.getProjectName()));
        newProjectPage
                .creatingProject(newProject)
                .isNewProjectCreated(newProject.getProjectName());
        log.info(format("Project '%s' has been created", newProject.getProjectName()));
    }
}