##############################################################################
#
#@Author - Khushboo Taneja khushiak48@gmail.com
#
##############################################################################
Feature: Radio Nav Drawers
  AS A user
  I WANT to see more content associated with navigation links
  So THAT I can easily find what I am looking for

  @Staions @ScenarioAssignment @todo
  Scenario Outline: Availability of all stations in station panel
    Given I can see the radio nav
    When I select Stations in the radio nav
    Then I can see the following stations in below order(First national stations after that nation stations)
      | radio1            |  1 |
      | 1xtra             |  2 |
      | radio2            |  3 |
      | radio3            |  4 |
      | radio4            |  5 |
      | radio4extra       |  6 |
      | 5live             |  7 |
      | 5livesportsextra  |  8 |
      | 6music            |  9 |
      | asiannetwork      | 10 |
      | worldserviceradio | 11 |
      | radioscotland     | 12 |
      | radionangaidheal  | 13 |
      | radioulster       | 14 |
      | radiofoyle        | 15 |
      | radiowales        | 16 |
      | radiocymru        | 17 |
    When I select the all stations button
    Then I am on the stations page
    And I close the browser

  @Staions @ScenarioAssignment @todo
  Scenario Outline: Selecting the networks from stations drawer
    Given I can see the radio nav
    When I select <stationName> in the radio nav
    Then I can see <currentURL>
    And I can see <logo>
      | stationName       | currentURL                  | logo               |
      | radio1            | http://www.bbc.co.uk/radio1 | bbc_radio_one logo |
      | 1xtra             |                             |                    |
      | radio2            |                             |                    |
      | radio3            |                             |                    |
      | radio4            |                             |                    |
      | radio4extra       |                             |                    |
      | 5live             |                             |                    |
      | 5livesportsextra  |                             |                    |
      | 6music            |                             |                    |
      | asiannetwork      |                             |                    |
      | worldserviceradio |                             |                    |
      | radioscotland     |                             |                    |
      | radionangaidheal  |                             |                    |
      | radioulster       |                             |                    |
      | radiofoyle        |                             |                    |
      | radiowales        |                             |                    |
      | radiocymru        |                             |                    |
    And I close the browser

  @Staions @ScenarioAssignment @todo @iplayerlogo
  Scenario Outline: Selecting the iPlayer radio logo
    Given I can see the radio nav
    When I click on iplayer radio logo
    Then I can see Radio homepage
    And I close the browser
