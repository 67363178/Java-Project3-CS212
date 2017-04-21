import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * FileMenuHandler implements ActionListener
 * This is called when the use clicked on file (Open),(Quit)
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class FileMenuHandler implements ActionListener{
	
Project3GUI myGUI;
Word updatedWord;
int counterForOpenfile=0; //see how many time a file is open
	public FileMenuHandler(Project3GUI gui){
		myGUI=gui;
	}
	
	/**
	 * actionPerformed
	 * see what menu name the user clicked on, then corresponded the following method.
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName=event.getActionCommand();
		
		if(menuName.equals("Open"))
			openFile();
		else if(menuName.equals("Quit"))
			System.exit(0);
		
	}//actionPerformed
	
	/**
	 * openFile()
	 * when menu name is open, then use this openFile() method.
	 * It will open a selected file
	 * 
	 */
	private void openFile(){
		JFileChooser chooser;
		int status;
		chooser=new JFileChooser();
		status =chooser.showOpenDialog(null);
		if(status==JFileChooser.APPROVE_OPTION)
			readSource(chooser.getSelectedFile());
	}//OpenFile
	
	// fill the linked lists and display them in the text areas (DONE!!!)
	/**
	 * readSource
	 * After opening a file, read the data from the file. 
	 * Stored them in linked lists and print it out.
	 * After the program finishing printing, command window will ask user to enter command
	 * ADD,DELETE,STOP
	 * ADD will insert a word -only word that follow the format will be added or a message window will pop up and say "TRY AGAIN"
	 * Delete will delete a word
	 * Stop will close the command window
	 * @param chosenFile the file that user want to open
	 */
	private void readSource(File chosenFile){
		
		
		String chosenFileName=chosenFile.getName();
		TextFileInput inFile=new TextFileInput(chosenFileName);
		
		String in=inFile.readLine(); //Read the input
		
	    
		
		while (in != null) {
	    	Word ReadWordFromFile= new Word(in);
	    	//go to GUI and find SortedWord/UnsortedWord then to the class SortedWordList then use the method add.
	    	myGUI.SortedWord.insert(ReadWordFromFile); 
	    	myGUI.UnsortedWord.insert(ReadWordFromFile);
	    	in=inFile.readLine();
	    }//Fill in LinkedList
	    
	    //Print it 
	    myGUI.SORTEDAREA.append(myGUI.SortedWord+"\n");
	    myGUI.UNSORTEDAREA.append(myGUI.UnsortedWord+"\n");
	    
	    //Commands
	    String Command,Word;
		String tempword="";
		String N_V=""; //check if the user input data in a correct way.
		
		int commaPosition, PPosition;//("<" <--- is PPosition
		boolean ShouldIResetTheText=false;	

		for(;;){
		    boolean CommandA=false,CommandD=false;
		    Command =JOptionPane.showInputDialog("Enter Command: (ADD, STOP, DELETE)");
		
		    //Stop
			if(Command.equalsIgnoreCase("STOP")){ //the user must enter Stop, such that it will updated the list. cancel means cancel 
				updatedLinkedList();
				break;
			}
			
			//ADD
			if(Command.substring(0,3).equalsIgnoreCase("ADD")){ 	//(0,3) =Add (VERB)	
				CommandA=true;
				Word=Command.substring(4,Command.length());		//Word = the string after "add"
				PPosition=Word.indexOf("(");
				commaPosition=Word.indexOf(",");
				
				try{//if user enter Add 123(V),231, it will catch and tell them to try again
				
					
					while(commaPosition!=-1&&PPosition!=-1){
						N_V=Word;
						if(N_V.substring(PPosition,PPosition+3).equals("(V)")||N_V.substring(PPosition,PPosition+3).equals("(N)")){
							tempword=Word.substring(0, commaPosition);
							Word TEMPWORD=new Word(tempword);
							myGUI.SortedWord.insert(TEMPWORD);
							myGUI.UnsortedWord.insert(TEMPWORD);
						}
						Word=Word.substring(commaPosition+1,Word.length());//commaPosition +1 so that it doesn't include ",";
						//update position
						commaPosition=Word.indexOf(",");	
						PPosition=Word.indexOf("(");
					}
				
				
					if(commaPosition==-1&&PPosition!=-1){
						N_V=Word;
						if(N_V.substring(PPosition,PPosition+3).equals("(V)")||N_V.substring(PPosition,PPosition+3).equals("(N)")){
							tempword=Word;
							Word TEMPWORD=new Word(tempword);
							TEMPWORD= new Word(tempword);
							myGUI.SortedWord.insert(TEMPWORD);
							myGUI.UnsortedWord.insert(TEMPWORD); // when it only has a word to add
						}
						
					}
					else JOptionPane.showMessageDialog(null, "Word does not match, try again. (V),(N)");
					
					//reset it
					for(int i=0; i<=1; i++){
						myGUI.SORTEDAREA.setText("");	
						myGUI.UNSORTEDAREA.setText("");	
						myGUI.SORTEDAREA.append("");
						myGUI.UNSORTEDAREA.append("");
					}
					
					myGUI.SORTEDAREA.append(myGUI.SortedWord+"\n");
					myGUI.UNSORTEDAREA.append(myGUI.UnsortedWord+"\n");
				
					
				}//try I am tired, give me an A
			
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "One of your word does not match the format Or Unknown Error occure. Try again.");
					myGUI.resetGUI();
					myGUI.SORTEDAREA.append(myGUI.SortedWord+"\n");
					myGUI.UNSORTEDAREA.append(myGUI.UnsortedWord+"\n");
				}
				
			}//add method
			
			//Delete
			if(Command.substring(0,6).equalsIgnoreCase("DELETE")){ //(0,6)=Delete
				CommandD=true;
				Word=Command.substring(7,Command.length());  //Word = the string after "delete"
				
				Word wordtodel=new Word(Word);
				
				if(!myGUI.SortedWord.contains(wordtodel)) JOptionPane.showMessageDialog(null, "No such word");
				
				myGUI.SortedWord.removeNode(wordtodel);
				myGUI.UnsortedWord.removeNode(wordtodel);
				
				ShouldIResetTheText=true;
				
				if(ShouldIResetTheText){
					for(int i=0; i<=1; i++){	
						myGUI.SORTEDAREA.setText("");	
						myGUI.UNSORTEDAREA.setText("");	
						myGUI.SORTEDAREA.append("");
						myGUI.UNSORTEDAREA.append("");
					}
					myGUI.SORTEDAREA.append(myGUI.SortedWord+"\n");
					myGUI.UNSORTEDAREA.append(myGUI.UnsortedWord+"\n");
				}
				
			}//delete method //it works
		
			if(!CommandA&&!CommandD){
				JOptionPane.showMessageDialog(null, "No such command");
			}
		}//read all the updated word into a link list. then use it to display N or V Done!
	}
	
	
	/**
	 * updatedLinkedList
	 * just to clear to text area and stored the data that is updated by the user into wordlist.
	 * So when the user selected verb or noun, it will show all updated verb/noun.
	 */
	public void updatedLinkedList(){
		myGUI.Iterator=myGUI.UnsortedWord.reset();
		
		while(myGUI.Iterator.hasNext()){
			updatedWord=myGUI.Iterator.next();
			myGUI.MyWordList.insert(updatedWord);
		}
	}
}

	
	
