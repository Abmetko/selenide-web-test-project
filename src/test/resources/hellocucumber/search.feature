Feature: Google search

  Scenario: Search in Google
    Given google page is open
    When input 'Minsk' in search field
    Then data should be displayed