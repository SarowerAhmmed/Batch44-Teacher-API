Feature: API CURD funtion testing
Scenario: as a User, i need to test API CURD function
Given add API CRUD fucntion url
When send request and check response for get data
And send request and check response for post data
And send request and check response for put data
And send request and check response for delete data
Then validate test data
