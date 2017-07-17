package hw3;

public class Queue {
	private Node first;
	private Node back;
	private int length;
	
	public Queue(){
		this.first = new Node();
		this.back = this.first;
		this.length = 0;
	}// end of constructor
	
	public boolean isEmptry(){
		if(length == 0){
			return true;
		}
		return false;
	}//end of isEmpty
	
	public void append(Object data){
		Node temp = new Node(data);
		if(this.isEmptry()){
			this.first = temp;
			this.back = this.first;
			this.length++;
		}//append if queue is empty
		else{
			this.back.setNext(temp);
			this.back = temp;
			this.length++;
		}//append if queue is not empty
	}//end of append
	
	public Node find(Object data){
		Node temp = this.first;
		for(int i = 0; i < this.length; i++){
			if(temp.getData() == data){
				return temp;
			}//return if found
			temp = temp.getNext();
		}//end of for loop
		return null;//return null if not found
	}//end of findByPosition
	
	public Node findByPosition(int position){
		Node temp = this.first;
		for(int i = 1; i < position; i++){
			temp = temp.getNext();
		}//end of for loop
		return temp;
	}//end of findByPosition
	
	public void delete(){
		//delete the last one
		Node temp = first;
		for(int i = 2; i < this.length; i++){
			temp = temp.getNext();
		}
		this.back = temp;
		this.length--;
	}
}
