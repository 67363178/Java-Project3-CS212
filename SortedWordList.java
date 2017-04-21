/**
 * SortedWordList
 * A sorted word list.
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class SortedWordList extends WordList{

	/**
	 * insert() 
	 * it adds into the list in order
	 */
	void insert(Word w){
		ListNode n=new ListNode(w);
		if(first.next==null){
			last.next=n;
			last=n;
			length++;
			return;
		}//append first node
		
		ListNode i=first; //temp node;
		while(i.next!=null&&i.next.data.compareTo(w)<0)
			i=i.next;
		if(i.next==null){
			last.next=n;
			last=n;
			length++;
		}
		else{
			n.next=i.next;
			i.next=n;
			length++;
		}
		
	}

 
	/**
	 * String toString
	 * just a toString method
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
	
}
