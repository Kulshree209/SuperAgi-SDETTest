#Author: kulshree.patil@gmail.com
#Summary : Writing an automation script to search and print the product
Feature: Search on Amazon

  Scenario Outline: Search for LG soundbar and print prices based on sorting order
    When I redirect to amazon
    And I search for "<productName>"
    Then I see and sort product names and prices

    Examples: 
      | productName |
      | lg soundbar |
