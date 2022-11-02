Feature: User should be able to create/delete and change project

  @tmsLink=case=3
    @testCaseID=3
  Scenario Outline: Create new project
    Given Valid username and password
    Given User logs in
    When User create new project '<projectName>' with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Then New project '<projectName>' displays on the Projects page
#    Post conditions
    And Delete project by code '<projectCode>'
    Examples:
      | projectName | projectCode | description | projectAccessType |
      | new project | NP          | ""          | private           |

  @tmsLink=case=4
    @testCaseID=4
  Scenario Outline: Delete project
    Given Create project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Valid username and password
    Then User logs in
    When User deletes '<projectName>'
    Then Project '<projectName>' should disappear from Projects page
    Examples:
      | projectName          | projectCode | description | projectAccessType |
      | project for deleting | PD          | "deleting"  | private           |

  @tmsLink=case=5
    @testCaseID=5
  Scenario Outline: Change project title
    Given Create project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Valid username and password
    Then User logs in
    When User change the old project title '<projectName>' on a new title '<newProjectName>'
    Then Confirmation message 'Project settings were successfully updated!' is appeared
    Then Project should change the name on '<newProjectName>'
    #    Post conditions
    And Delete project by code '<projectCode>'
    Examples:
      | projectName    | projectCode | description            | projectAccessType | newProjectName |
      | change project | CP          | "project for changing" | private           | new name       |