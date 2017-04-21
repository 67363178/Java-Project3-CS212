/**
 * Word
 * Where the data stored
 * Created on April 15, 2016
 * @author chunyiu
 *
 */

public class Word {
	protected String theWord;
	

	/**
	 * Constructor
	 */
	public Word(){
		theWord="";
	}
	
	public Word(String w) {
		theWord=w;
	}
	
	/**
	 * getWord
	 * get method
	 * @return the Word
	 */
	public String getWord(){
		return theWord;
	}

	/**
	 * toString method
	 * 
	 */
	public String toString(){
		return theWord;
	}

	/**
	 * compareTo override so I can pass word into it
	 * @param w1 is the data
	 * @return -1,0,1
	 */
	public int compareTo(Word w1) { //this should return 1(greater),0(equal),-1(smaller)
		return this.theWord.compareTo(w1.getWord());
	}

	
}
