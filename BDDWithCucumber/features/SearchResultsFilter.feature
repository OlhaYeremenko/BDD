Feature:
  As a user
  I want to filter search results
  So not to spent long time on searching the specific product

  @ScenarioWithMaxAndMinPrice
  Scenario:
    Given I am on the "http://pn.com.ua" site
    When I select "Компьютеры" as products category
    And I select "Настольные компьютеры" as products subcategory
    And I set maximum price to "6000"
    And I set minimum price to "3000"
    Then I see expected result

  @ScenarioForSearchProductWithCheapestPrice
  Scenario:
    Given  I am on the "http://pn.com.ua" site
    When I select "Компьютеры" as products category
    And I select "Настольные компьютеры" as products subcategory
    And I sort products by price
    And I get the product name with cheapest price
    And I search that product by name
    Then I see that product

  @ScenarioForManufacturer
  Scenario:
    Given I am on the "http://pn.com.ua" site
    When I select "Компьютеры" as products category
    And I select "Настольные компьютеры" as products subcategory
    And I select "ASUS" as manufacturer
    Then I see that products number equals to expected on the page
