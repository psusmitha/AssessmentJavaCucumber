#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Verifying page response, console errors and links in given page
  

  @ConsoleError
  Scenario Outline: Verifying Console errors in given pages
    	When user launch the "<browsername>" browser
    	Then I verify console errors in "<url>" page
    	
    	Examples:
    		|browsername| url |
    		|chrome| https://www.w3.org/standards/badpage |
    		|chrome| https://www.w3.org/standards/webofdevices/multimodal|
    		|chrome| https://www.w3.org/standards/webdesign/htmlcss| 
    	
  @ResponseValidation
  Scenario Outline: Verifying page response from given pages
    #	When I navigate to "<url>" page
    	Then I verify page response from "<url>" page
    	
    	Examples:
    		| url |
    		| https://www.w3.org/standards/badpage |
    		| https://www.w3.org/standards/webofdevices/multimodal |
    		| https://www.w3.org/standards/webdesign/htmlcss |
