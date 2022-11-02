Feature: API tests

  Scenario: API test for getting projects list
    When making request for getting projects list
    Then getting project list

#  Scenario: API test for creating new project
#    When making request for creating new project
#    Then new project is created
#
#  Scenario: API test for deleting project by 'projectCode'
#    When deleting project by projectCode
#    Then project is deleted
#
#  Scenario: Getting certain test case
#    When making request for getting certain test case
#    Then certain test case is received
#
#  Scenario: Creating test case
#    When making request for creating new test case
#    Then new test case is created
#
#  Scenario: Delete test case
#    When making request for deleting test case
#    Then test case is deleted
#
#  Scenario: Getting all test cases
#    When making request for getting list of test cases
#    Then getting test cases list
#
#  Scenario: Test for creating new test run in project
#    When making request for creating new test run
#    Then new test run is created
#
#  Scenario: Test for getting all test runs in project
#    When making request for getting all tests run of the project
#    Then getting list of all tests runs
#
#  Scenario: Test for deleting test run
#    When making request for deleting test run
#    Then test run is deleted
#
#  Scenario: Test for creating test plan
#    When making request for creating test plan
#    Then test plan is created