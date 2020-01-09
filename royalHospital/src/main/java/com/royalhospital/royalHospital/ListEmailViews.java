package com.royalhospital.royalHospital;

// All imports
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Store all View objects
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class ListEmailViews {

	/**
	 * ArrayList of views
	 */
	private static ArrayList<JFrame> allEmailView = new ArrayList<JFrame>();

	// All gets
	public static ArrayList<JFrame> getAllEmailView() {
		return allEmailView;
	}

	public static void setAllEmailView(ArrayList<JFrame> allEmailView) {
		ListEmailViews.allEmailView = allEmailView;
	}

}
