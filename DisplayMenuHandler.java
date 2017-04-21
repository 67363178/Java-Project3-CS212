import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * DisplayMenuHandler
 * A menu handler that used when user click on display
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class DisplayMenuHandler implements ActionListener {

	
	Project3GUI myGUI;
	
	public DisplayMenuHandler(Project3GUI gui){
		myGUI=gui;
	}
	
	@Override
	/**
	 * actionPerformed
	 * if the user selected Verb/Noun, it will correspond the following method 
	 * displayVerb/displayNoun both method are in the Project3GUI class
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName=event.getActionCommand();
		
		if(menuName.equals("Verb")){
			
			myGUI.displayVerb();
			
		}
		else if(menuName.equals("Noun"))
			myGUI.displayNoun();
	}
	

}
