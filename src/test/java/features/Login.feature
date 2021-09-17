Feature: Application Login

Scenario: Login with valid credentials

Given open any browser
And Navigate to loginpage
When User enters username as "phaniatw2@gmail.com" and password as "atw@123" in to the fileds
And User clicks on login button
Then verify user is able to login successfully