/**
 * Unsorted Word List, it extends WordList
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class UnsortedWordList extends WordList{
	
	/**
	 * add the Word into the list (unsorted)
	 */
	public void insert(Word w){
		ListNode n=new ListNode(w);
		last.next=n;
		last=n;
		length++;
	}
	
	/**
	 * a toString method
	 */
	public String toString(){
		ListNode p=first.next;
		String returnString="";
		
		while(p!=null){
			returnString+= p.data+"\n";
			p=p.next;
		}
		
		return returnString;
	}
	
}
