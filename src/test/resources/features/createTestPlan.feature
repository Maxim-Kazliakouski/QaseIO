Feature: User should be able to create/change and delete test plan

  Background:
    Given Valid username and password
    When User logs in
    Then User gets to the Projects page

  @tmsLink=case=9
    @testCaseID=9
  Scenario Outline: Create new test plan
    Given Create project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Create test case '<testCaseName>' via api in project with code '<projectCode>'
    When User create new test plan '<testPlanName>' in project '<projectName>'
    Then Confirmation message 'Test plan was created successfully!' is appeared
    Then Test plan '<testPlanName>' is displayed in Test Plans section
#    Post condition
    And Delete project by code '<projectCode>'
    Examples:
      | projectName       | projectCode | description | projectAccessType | testCaseName  | testPlanName   |
      | Project test plan | PFTP        | ""          | private           | authorization | test plan auth |

  @tmsLink=case=10
    @testCaseID=10
  Scenario Outline: Change test plan
    Given Create project '<projectName>' via API with params: project code: '<projectCode>', description: '<description>', project access type: '<projectAccessType>'
    Given Create test case '<testCaseName1>' via api in project with code '<projectCode>'
    Given Create test case '<testCaseName2>' via api in project with code '<projectCode>'
    Given Create test plan '<testPlanName>' with cases id: '<casesID's>', project code '<projectCode>' via api
    When User change the test plan name on '<newTestPlanName>', project code is '<projectCode>'
    Then Confirmation message 'Test plan was edited successfully!' is appeared
    Then New test plan name '<newTestPlanName>' is displayed on Test plan page
    #    Post condition
    And Delete project by code '<projectCode>'
    Examples:
      | projectName              | projectCode | description | projectAccessType | testCaseName1 | testCaseName2 | testPlanName     | casesID's | newTestPlanName    |
      | Project change test plan | PCTP        | ""          | private           | basket        | adding        | test plan buying | 1,2       | new test plan name |