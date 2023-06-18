Feature: Google map

  Scenario: Map in Google
    Given google maps page is open
    When input 'Minsk' in maps search field
    And click search button