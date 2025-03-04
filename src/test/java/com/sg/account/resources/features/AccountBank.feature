Feature: Bank Account Operations

  Scenario: Deposit money into an account
    Given the account has an initial balance of 0
    When the client deposits 100
    Then the account balance should be 100

  Scenario: Withdraw money from an account
    Given the account has an initial balance of 200
    When the client withdraws 100
    Then the account balance should be 100

  Scenario: Withdraw more than available balance
    Given the account has an initial balance of 50
    When the client tries to withdraw 100
    Then the system should return an error message "Insufficient funds"

  Scenario: Check account balance
    Given the account has an initial balance of 150
    When the client checks the balance
    Then the system should return 150

  Scenario: Get account statement
    Given the client has performed transactions
    When the client requests the account statement
    Then the system should return a list of transactions