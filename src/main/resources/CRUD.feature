@API_Regression @API_Funstional
Feature: API HTTP CRUD function test
Scenario: As a user, i can get the data from my application
Given put get url
When send get request & response
Then validate get data with test cases

Scenario: As a user, i can send or post data from my application
Given put post url
When send post request and get response
Then validate post data with test cases

Scenario: As a user, i can update or put data from my application
Given put put url
When send put request and get response
Then validate put data with test cases

Scenario: As a user, i can remove the data from my application
Given put delete url
When send delete request & response
Then validate delete data with test cases
