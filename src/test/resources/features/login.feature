Feature: Login

  @tmsLink=case=1
  @testCaseID=1
  Scenario: Successful login
    Given Valid username and password
    When  User logs in
    Then User gets to the Projects page

#  @tmsLink=case=2
#  @testCaseID=2
#  Scenario: Unsuccessful login
#    Given Invalid username and password
#    When User logs in
#    Then User gets error message 'These credentials do not match our records.'