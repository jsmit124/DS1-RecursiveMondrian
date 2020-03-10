Expansions:

	1. Uses lines of random width with max width depending on user input at startup
		*Input is found on the final dialog at program startup and the call to generate random width is on line 136, within
		MondrianArt::drawRectangleOnCanvas()
		
	2. Program has 10% chance to draw a checkered pattern on a rectangle
		*Original call is on line 143, within the MondrianArt::drawRectangleOnCavas() and functionality is refactored out
		into the private helper method, MondrianArt::drawPattern() 
	