package hw3;

public class Stack {
	private Node first;
	private Node back;
	private int length;
	
	public Stack(){
		this.back = new Node();
		this.first = this.back;
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
			this.back = temp;
			this.first = this.back;
			length++;
		}//append if queue is empty
		else{
			temp.setNext(this.first);
			this.first = temp;
			this.length++;
		}//append if queue is not empty
	}//end of append
	
	public Node findByData(Object data){
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
		//delete the first one
		this.first = this.first.getNext();
		this.length--;
	}
	
	public int getLength(){
		return this.length;
	}
}
