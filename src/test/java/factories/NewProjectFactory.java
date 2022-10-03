package factories;

import dto.NewProject;

public class NewProjectFactory {
    public static NewProject getByAccessType(String projectName, String accessType) {
        if (accessType.equals("Private")) {
            return NewProject.builder()
                    .projectName(projectName)
                    .projectCode("")
                    .description("Private project")
                    .projectAccessType("Private")
                    .memberAccess("Add all members to this project")
                    .build();
        } else {
            return NewProject.builder()
                    .projectName(projectName)
                    .projectCode("")
                    .description("Public project")
                    .projectAccessType("Public")
                    .build();
        }
    }
}