Feature:  pet api

Scenario: Verify the pet api feature

Given create pet api payload 
When user calls post request to crate pet
Then He should get status code as 200
