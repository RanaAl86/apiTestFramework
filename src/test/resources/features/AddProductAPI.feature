Feature: Add new product using POST API

  Scenario Outline: Successfully add a new product
    Given the API for adding product is "url"
    When I send a POST request with the following product details "<title>", "<price>","<description>","<image>","<category>"
    Then the response status code is "200"
    And the response body should contain product details
    And the response should include an "id" field

    Examples:
      | title     | price  | description          | image                                                                     | category    |
      | Xbox      | 525.0  | Gaming Console       | https://m.media-amazon.com/images/I/51QU5IgXA7L._AC_UF1000,1000_QL80_.jpg | electronics |
      | Gaming PC | 699.00 | Full setup Gaming PC | https://i.ebayimg.com/images/g/wO4AAOSwaJ1kgN2S/s-l1200.jpg               | electronics |