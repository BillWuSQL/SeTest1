package org.pattern.state;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Machine machine=new Machine(10);  
		for(int i=0;i<11;i++){  
	         System.out.println(machine);  
	         machine.insertQuerter();  
	         machine.turnCrank();  
	         }  
	}

}
