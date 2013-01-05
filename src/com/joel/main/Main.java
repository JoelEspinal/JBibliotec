
package com.joel.main;

import javax.swing.UIManager;

import com.joel.controllers.MainController;
import com.joel.tools.LoanHelper;

public class Main {
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		
		MainController.init();

	}
}
