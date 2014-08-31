package org.pattern.adapter;

public class Adapter implements Target {

	private Adaptee adaptee;
	public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
	
	
	public void sampleOperation1() {
		adaptee.sampleOperation1();
	}
	
	public void sampleOperation2() {
		// TODO Auto-generated method stub
		
	}
	
}
