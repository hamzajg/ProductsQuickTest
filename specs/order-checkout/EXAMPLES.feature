# order-checkout — specifications by example
# Scenario tags @w<N> map items to delivery waves (see WAVES.md / BACKLOG.md).

Feature: order-checkout
  A customer buys products; the products service holds the stock, the orders
  service runs the checkout and calls the other two services over HTTP.

  # ---- Wave 1: stock reservation in the products service ----------------

  @w1 @happy-path
  Scenario: Reserve stock within availability
    Given a product that exists with available quantity 10
    When a reserve request for quantity 4 succeeds
    Then the product's available quantity is 6

  @w1 @edge-case
  Scenario: Reserving beyond availability is rejected
    Given a product that exists with available quantity 3
    When a reserve request for quantity 5 is rejected
    Then the response status is 409
    And the product's available quantity is still 3

  @w1 @edge-case
  Scenario: Releasing reserved stock restores availability
    Given a product with available quantity 6 after a reservation of 4
    When a release request for quantity 4 succeeds
    Then the product's available quantity is 10

  # ---- Wave 2: the orders service and the cross-service checkout --------

  @w2 @happy-path
  Scenario: A customer places an in-stock order
    Given a customer that exists
    And a product that exists with available quantity 10 and unit price 5.00
    When the customer places an order for quantity 2 of that product
    Then the order is stored with status PLACED
    And the order total is 10.00

  @w2 @happy-path
  Scenario: A placed order decrements stock
    Given a product that exists with available quantity 10
    When an order for quantity 2 of that product is placed
    Then the product's available quantity is 8

  @w2 @edge-case
  Scenario: An unknown customer cannot order
    Given no customer exists with the requested id
    When that customer places an order
    Then the response status is 404
    And the response error is CUSTOMER_NOT_FOUND
    And no order is stored

  @w2 @edge-case
  Scenario: An unknown product cannot be ordered
    Given a customer that exists
    And no product exists with the requested id
    When the customer orders that unknown product
    Then the response status is 404
    And the response error is PRODUCT_NOT_FOUND

  @w2 @edge-case
  Scenario: Out-of-stock rolls the checkout back
    Given a customer that exists
    And a product with available quantity 10
    And a second product with available quantity 1
    When the customer orders quantity 1 of the first product and quantity 5 of the second
    Then the response status is 409
    And the response error is PRODUCT_OUT_OF_STOCK
    And the first product's available quantity is still 10
    And no order is stored

  # ---- Wave 3: order history behind the gateway -------------------------

  @w3 @happy-path
  Scenario: Order history is readable through the gateway
    Given a customer that exists with one placed order
    When the orders are listed through the gateway
    Then the placed order appears in the list
    And fetching that order by id returns the same order

  @w3 @edge-case
  Scenario: An unknown order id is not found
    When an order is fetched by an id that was never placed
    Then the response status is 404
    And the response error is ORDER_NOT_FOUND
