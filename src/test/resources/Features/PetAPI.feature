Feature:  pet api

Scenario: Verify the pet api feature

Given create pet api payload 
When user calls post request to crate pet
Then He should get status code as 200



Scenario: Verify that user can update the and metadata for a pet
Given create file & metadata payload 
When user calls post request to update the pet data 
Then He should get status code as 200

@Block
Scenario: Verify that the user is able to fetch the pet details
When user hits get request using pet id 
Then he should get pet details