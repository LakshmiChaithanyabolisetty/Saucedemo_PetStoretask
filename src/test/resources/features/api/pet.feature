Feature: Pet Endpoints

  @AddNewPet
  Scenario Outline: Add a new pet with valid details
    Given I have pet details with id "<id>", name "<name>", and status "<status>"
    When I send a request to add the pet
    Then the response status code for adding the pet should be 200
    And the pet name in the response should be "<name>"
    And the pet status in the response should be "<status>"

    Examples:
      | id    | name   | status    |
      | 12345 | Fluffy | available |

  @GetPetById
  Scenario Outline: Retrieve pet details by ID
    Given I have a valid pet ID "<petId>"
    When I send a request to get the pet details
    Then the response status code for fetching pet by id should be 200
    And the pet ID in the response should be "<petId>"
    And the pet name in the response should be "<name>"
    And the pet status in the response should be "<status>"

    Examples:
      | petId | name   | status    |
      | 1     | doggie | available |
#

  @UpdatePet
  Scenario Outline: Update an existing pet with valid details
    Given I have existing pet details with id "<id>", name "<name>", and status "<status>"
    When I send a request to update the pet details
    Then the response status code for updating pet should be 200
    And the updated pet name in the response should be "<name>"
    And the updated pet status in the response should be "<status>"

    Examples:
      | id    | name          | status |
      | 12345 | UpdatedFluffy | available   |

  @DeletePet
  Scenario Outline: Delete a pet using a valid pet ID
    Given I have a valid pet ID "<petId>"
    When I send a request to delete the pet
    Then the response status code for deleting pet should be 200
    And the response message should confirm pet deletion for ID "<petId>"

    Examples:
      | petId |
      | 12345 |

  @FindPetsByStatus
  Scenario Outline: Retrieve pets by status
    Given I have a valid pet status "<status>"
    When I send a request to find pets by status
    Then the response status code for finding pet by status should be 200
    And all pets in the response should have the status "<status>"

    Examples:
      | status     |
      | available  |
      | pending    |
      | sold       |

  @GetPetByIdErrorHandling
  Scenario Outline: Retrieve pet details with invalid or nonexistent ID
    Given I have an invalid or nonexistent pet ID "<petId>"
    When I send a request to get the pet details
    Then the response status code for fetching pet by id should be <statusCode>
    And the error message in the response should indicate "<errorMessage>"

    Examples:
      | petId | statusCode | errorMessage  |
      | 1     | 404        | Pet not found |