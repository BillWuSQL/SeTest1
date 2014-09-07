package org.pattern.decorator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Human person = new Person(new xxxx(new xxx));  
		Human person = new Person();  
		
		Decorator decorator = (new Decorator_first(new Decorator_zero(person)));  
		
		decorator.wearClothes();
		decorator.walkToWhere();
	}

}
