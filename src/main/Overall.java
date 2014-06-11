package main;

import gUIModule.GUI;

/**
 * Overall contains the main function which creates the new GUI and opens Grimoire V1.0
 *@author Robert Mills
 *@author Joshua Lant
 */
public class Overall {

	public static GUI bookMain; 
	
	public static void main(String[] args) {
		bookMain = new GUI("bookMainPanel");
	}
}
