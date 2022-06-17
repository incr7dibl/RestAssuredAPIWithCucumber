Feature: Validating place API
@Addplace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add place payload with <name>,<language>,<address>
When user call "addPlaceAPI" with post http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id is created maps to <name> using "getPlaceAPI"
Examples:
|name|language|address|
|Harsh|English|Kolhapur|


@Deleteplace
Scenario: Verify if delete place funcationality is working
Given DeleteplaceAPI payload
When User "deletePlaceAPI" with place_ID
Then the API call is success with status code 200
And "status" in response body is "OK"