import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * Project3 GUI
 * WordList- the updated word will be in the WordList
 * SortedWord	-Sorted Word (FILE)
 * UnsortedWord	-Unsorted Word(File)
 * SortedVerb	-Sorted Verb 	(Display Verb)
 * SortedNoun	-Sorted Noun	(Display Noun)
 * UnsortedVerb	-Unsorted Verb	(Display Verb)
 * UnsortedNoun	-Unsorted Noun	(Display Noun)
 * Container myContentPane; 
 * TextArea SORTEDAREA=new TextArea(); 
 * TextArea UNSORTEDAREA=new TextArea(); 
 * These are just From JFRAME
 * JMenuBar menuBar=new JMenuBar();//pass this s.t file and display show up in the same menuBar
 * LinkedListIterator Iterator; //to reset the linked list
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class Project3GUI extends JFrame {
	
	WordList MyWordList, SortedWord, UnsortedWord, SortedVerb,SortedNoun, UnsortedVerb, UnsortedNoun;
	Container myContentPane;
	TextArea SORTEDAREA=new TextArea();
	TextArea UNSORTEDAREA=new TextArea();
	JMenuBar menuBar=new JMenuBar();//pass this s.t file and display show up in the same menuBar
	LinkedListIterator Iterator; //to reset the linked list
	
	//For displayVerb and noun
	String N_OR_V; //find if its Nor V
	int Position;//( position
	String COMMAND;
	String findVerb, Letter;//find the letter
	Word toStringVerb; //convert it to String
	Word toStringNoun;
	//see how many times user click on Verb or Noun, if its not =0, don't insert
	//so it will not add the data over and over again
	int counterForV =0; 
	int counterForN =0;
	//set up my GUI
	/**
	 * Set up Project3GUI
	 * @param title the title to set
	 */
	public Project3GUI(String title){
		setTitle(title);
		setLocation(100,100);
		setSize(500, 500);
		createFileMenu(menuBar);
		createDisplayMenu(menuBar); //They will share the same menu Bar
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SortedWord=new SortedWordList(); //this will go to SortedWordList();
		UnsortedWord=new UnsortedWordList();//this will go to UnsortedWordList(); (just add)
		SortedVerb=new SortedWordList();
		SortedNoun=new SortedWordList();
		UnsortedVerb=new UnsortedWordList();
		UnsortedNoun=new UnsortedWordList();

		MyWordList=new UnsortedWordList();
		
		myContentPane=getContentPane();
		myContentPane.setLayout(new GridLayout(1,2));
		myContentPane.add(UNSORTEDAREA);
		myContentPane.add(SORTEDAREA);
		
		setVisible(true);
	}
/**
 * set up DisplayMenu. menuBar will share with FileMenu.
 * @param menuBar the menuBar to set
 */
	private void createDisplayMenu(JMenuBar menuBar) {
		JMenuItem item;
		 JMenu fileMenu = new JMenu("Display");
		 DisplayMenuHandler dmh  = new DisplayMenuHandler(this);

		 item = new JMenuItem("Verb");    //Unsorted
		 item.addActionListener( dmh );
		 fileMenu.add( item );

		 fileMenu.addSeparator(); //add a horizontal separator line
		    
		 item = new JMenuItem("Noun");       //Sorted
		 item.addActionListener( dmh );
		 fileMenu.add( item );

		 setJMenuBar(menuBar);
		 menuBar.add(fileMenu);
		
	}
/**
 * set up FileMenu, menuBar will share with DisplayMenu
 * @param menuBar the menuBar to set
 */
	private void createFileMenu(JMenuBar menuBar) {
		 JMenuItem   item;
	      JMenu       fileMenu = new JMenu("File");
	      FileMenuHandler fmh  = new FileMenuHandler(this);

	      
	      //File Open
	      item = new JMenuItem("Open");    //Open...
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      fileMenu.addSeparator();           //add a horizontal separator line
	    
	      item = new JMenuItem("Quit");       //Quit
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      
	      setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	     
		
	}
	/**
	 * reset GUI- To clear the textArea.
	 */
	public void resetGUI(){
		//reset
				for(int i=0; i<=1; i++){//without for loop, you will need to click verb twice to reset it. I Assume the program cant execute the codes below at the same time so I added a for loop, make sure that it will reset both Area first then run the reset of the codes 
				SORTEDAREA.setText("");
				SORTEDAREA.append("");
				
				UNSORTEDAREA.setText("");	
				UNSORTEDAREA.append("");
				}// This will reset the GUI

	}
	/**
	 * displayVerb
	 * Display Verb
	 * Use when use selected Verb in the Display Menu.
	 * 
	 */
	public void displayVerb(){
		
		if(counterForV!=0){//show the user the same list
			resetGUI();
			UNSORTEDAREA.append(UnsortedVerb+"\n");
			SORTEDAREA.append(SortedVerb+"\n");
		}
		else{
			resetGUI();
		
			//since MyWordList has stored the data that user updated, use it.
			Iterator=MyWordList.reset();
				while(Iterator.hasNext()){
					toStringVerb=Iterator.next();
					findVerb=toStringVerb.toString(); //toString method
	
					Position=findVerb.indexOf("(");
					Letter=findVerb.substring(Position+1, findVerb.length()-1);
					//System.out.println(LetterV);
						if(Letter.equals("V")){
					//System.out.println(findVerb+"we find it");
							SortedVerb.insert(toStringVerb);
							UnsortedVerb.insert(toStringVerb);
						}
				
				}
			
			UNSORTEDAREA.append(UnsortedVerb+"\n");
			SORTEDAREA.append(SortedVerb+"\n");
		}
		counterForV++;	
	}//display Verb method

	/**
	 * displayNoun
	 * Display Noun.  Use when use selected Noun in the Display Menu
	 */
	public void displayNoun() {
		
		if(counterForN!=0){//show the user the same list
			resetGUI();
			UNSORTEDAREA.append(UnsortedNoun+"\n");
			SORTEDAREA.append(SortedNoun+"\n");
		}
			else{
				resetGUI();
				Iterator=MyWordList.reset();
				while(Iterator.hasNext()){
					toStringNoun=Iterator.next();
					findVerb=toStringNoun.toString(); //toString method
		
					Position=findVerb.indexOf("(");
					Letter=findVerb.substring(Position+1, findVerb.length()-1);
						if(Letter.equals("N")){
							SortedNoun.insert(toStringNoun);
							UnsortedNoun.insert(toStringNoun);
						}
				
				}
		
				UNSORTEDAREA.append(UnsortedNoun+"\n");
				SORTEDAREA.append(SortedNoun+"\n");
				
				counterForN++;
			}
			
	}//displayNoun method
	
	
}

