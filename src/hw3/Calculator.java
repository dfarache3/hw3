package hw3;

import java.io.*;
import java.util.regex.*;

public class Calculator {
	
	public static void main(String[] args) throws IOException{
		System.out.println("enter your polynomial");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader grab = new BufferedReader(isr);
		String string_entered = grab.readLine();
		String [] input = string_entered.split(" ");
		System.out.println(checkReversePolish(input));
	}//end of main method
	
	public static String calculate(String [] monomial){
		double solution;
		String output = null;
		double a = Double.parseDouble(monomial[1]);
		String symbol = monomial[2];
		double b = Double.parseDouble(monomial[3]);
		
		switch(symbol){
		
		case("+"):
			solution = a+b;
			output = Double.toString(solution);
		break;
		
		case("-"):
			solution = a-b;
			output = Double.toString(solution);
		break;
		
		case("*"):
			solution = a*b;
			output = Double.toString(solution);
		break;
		
		case("/"):
			solution = a/b;
			output = Double.toString(solution);
		break;
		
		default:
			output = null;
		break;
		
		}//end of switch
		
		return output;
		
	}//end of calculate
	
	public static String calculateInfix(String [] input){
		Stack process = new Stack();
		for(int i = 0; i < input.length; i++){
			process.append(input[i]);
			if(input[i].equals(")")){
				String[] monomial = new String[5];
				for(int j = 0; j < 5; j++){
					monomial[j] = (String) process.findByPosition(1).getData();
					process.delete();
				}
				String temp = calculate(monomial);
				process.append(temp);
			}//calculate when a monomial is form
		}//check through input
		return (String)process.findByPosition(1).getData();
	}
	
	public static boolean checkInfix(String [] input){
		Stack process = new Stack();
		for(int i = 0; i < input.length; i++){
			Pattern p = Pattern.compile("[\\d()+*-/]");
			Matcher m = p.matcher(input[i]);
			if(!m.find()){System.out.println("not valid character!");return false;}
			process.append(input[i]);
			if(input[i].equals(")")){
				if(((String)process.findByPosition(5).getData()).equals("(")){
					for(int j = 0; j < 5; j++){
						process.delete();
					}//end of delete
				}//end of check (
				else{System.out.println("it is not well formed!");return false;}
				process.append(new Node());
			}//end of if reach )
		}//check through input
		if(process.getLength() == 1){
			return true;
		}
		else{System.out.println("it is not well formed!");return false;}
	}
	
	public static String calculateReversePolish(String [] input){
		Stack process = new Stack();
		
		for(int i = 0; i < input.length; i++){
			process.append(input[i]);
			Pattern p = Pattern.compile("\\D");//when using regex [/*-+] "-" cannot be identified????
			Matcher m = p.matcher(input[i]);
			if(m.find()){
				String[] monomial = new String[5];
				monomial[0] = "(";
				monomial[4] = ")";
				monomial[2] = (String)process.findByPosition(1).getData();
				monomial[3] = (String)process.findByPosition(2).getData();
				monomial[1] = (String)process.findByPosition(3).getData();
				for(int j = 0; j < 3; j++){
					process.delete();
				}//delete the first three term in the Stack
				String temp = calculate(monomial);
				process.append(temp);
			}
		}//end of loop
		
		return (String)process.findByPosition(1).getData();
	}//end of calculateReversePolish
	
	public static boolean checkReversePolish(String[] input) {
		Stack process = new Stack();

		for (int i = 0; i < input.length; i++) {
			Pattern p = Pattern.compile("[\\d+*-/]");
			Matcher m = p.matcher(input[i]);
			if(!m.find()){System.out.println("not valid character!");return false;}
			process.append(input[i]);
			
			Pattern p2 = Pattern.compile("\\D");// when using regex [/*-+] "-"cannot be identified????
			Matcher m2 = p2.matcher(input[i]);
			if (m2.find()) {
				for (int j = 0; j < 3; j++) {
					process.delete();
				} // delete the first three term in the Stack
				Node temp = new Node();
				process.append(temp);
			}
		} // end of loop
		if(process.getLength() == 1){
			return true;
		}
		else{System.out.println("reverse polish is not well formed!");return false;}
	}// end of checkReversePolish

}
