/**
 * WordList
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public abstract class WordList {

	ListNode ln=new ListNode(null);
	protected ListNode first=ln;
	protected ListNode last=ln;
	protected int length =0;
	
	//Constructor
	public WordList(){
		
	}
	/**
	 * insert()
	 * @param w the data the user want to add
	 */
	abstract void insert(Word w);
	
	/**
	 * get Length
	 * @return length
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * toString method
	 */
	public String toString(){
		ListNode p=first.next;
		String returnString="";
		while(p!=null){
			returnString+=p.data+"\n";
			p=p.next;
		}
		return returnString;
	}
	
	/**
	 * LinkedListIterator reset
	 * @return new LinkedListIterator(first.next)
	 */
	public LinkedListIterator reset(){
		return new LinkedListIterator(first.next);
	}

	/**
	 * contains -see if it contains the data we want to delete
	 * @param wordtodel the word to delete
	 * @return true or false
	 */
	public boolean contains(Word wordtodel){
		ListNode curr = first.next;
		while (curr != null){
			if (curr.data.compareTo(wordtodel)==0) return true;
			curr = curr.next;
		}///while
		return false;
	}//contains

	/**
	 * removeNode
	 * if it contains the data we want to delete, then we delete it
	 * @param wordtodel word to delete
	 */
	public void removeNode(Word wordtodel){
		//only runs the body of the method if the word, is on list
		if(contains(wordtodel)){
		ListNode p = first.next;
			if(p.data.compareTo(wordtodel)==0){
				first.next = p.next;
				p.next = null;
			}//if
			else if (p.data.compareTo(wordtodel)!=0){
				ListNode curr = first.next.next;
				while(curr.data.compareTo(wordtodel)!=0){
					curr = curr.next;
					p = p.next;
				}//while
				p.next = curr.next;
				curr.next = null;
			}//else
		}// if contains
		else return;
	}//removeNode
	
	
}
