Feature:  Login feature

  Scenario: Login with valid credentials
  
		Given the user opens the OrangeHRM login page
		
		When the user enters valid credentials
		
		And the user clicks the login button
		
		Then the dashboard should be displayed
