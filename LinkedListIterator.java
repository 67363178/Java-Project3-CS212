/**
 * LinkedListIterator
 * Such that user can't have direct access to the list 
 * Created on April 15, 2016
 * @author chunyiu
 *
 */
public class LinkedListIterator {
	private ListNode node;
	
	/**
	 * LinkedListIterator
	 * @param first the first or head node.
	 */
	public LinkedListIterator(ListNode first){
		node =first;
	}
	
	/**
	 * hasNext see if the node has next, if not return false.
	 * @return true or false value
	 */
	public boolean hasNext(){
		return(node!=null);
	}
	

	/**
	 * next() go to the next node/data.
	 * @return the current Data
	 */
	public Word next(){
		if(node==null) throw new NullPointerException("Linked list empty.");
		Word currentData=node.data;
		node=node.next;
		return currentData;
	}
	
}
