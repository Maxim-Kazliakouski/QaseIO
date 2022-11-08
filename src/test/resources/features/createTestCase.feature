Feature: User should be able to create/change and delete test cases

  Background: User logged into the app
    Given Valid username and password
    When User logs in
    Then User gets to the Projects page

  @tmsLink=case=6
    @testCaseID=6
  Scenario Outline: Create new test case
    Given Project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given User goes to created project '<projectName>'
    When User create new test case with title '<testCaseName>'
    Then Confirmation message 'Test case was created successfully!' is appeared
    And New test case '<testCaseName>' is created
#    Post condition
    And Delete project by code '<projectCode>'
    Examples:
      | projectName                | projectCode | description | projectAccessType | testCaseName  |
      | Project for creating cases | PFCCASES    | ""          | private           | new test case |

  @tmsLink=case=7
    @testCaseID=7
  Scenario Outline: Delete test case
    Given Project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Create test case '<testCaseName>' via api in project with code '<projectCode>'
    When User goes to created project '<projectName>'
    When User delete test case '<testCaseName>'
    Then Confirmation message '1 test case was successfully deleted' is appeared
    And Test case '<testCaseName>' is deleted
#    Post condition
    And Delete project by code '<projectCode>'
    Examples:
      | projectName                | projectCode | description | projectAccessType | testCaseName  |
      | Project for deleting cases | DC          | ""          | private           | new test case |

  @tmsLink=case=8
    @testCaseID=8
  Scenario Outline: Change test case name
    Given Project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Create test case '<testCaseName>' via api in project with code '<projectCode>'
    When User goes to created project '<projectName>'
    When User change test case name on '<newTestCaseName>' in project '<projectCode>'
    Then Test case has a new title '<newTestCaseName>'
#    Post condition
    And Delete project by code '<projectCode>'
    Examples:
      | projectName                | projectCode | description | projectAccessType | testCaseName  | newTestCaseName |
      | Project for changing cases | CC          | ""          | private           | new test case | new name        |