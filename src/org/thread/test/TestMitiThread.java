package org.thread.test;

public class TestMitiThread {
	
	public static void main(String args[]) {
		
//		System.out.println(Math.random() * 10);
		
		System.out.println(Thread.currentThread().getName() + "thread start ");
		
		new MitiSay("A").start();
		new MitiSay("B").start();
		
		System.out.println(Thread.currentThread().getName() + "thread end ");

	}
	
	
	
}




class MitiSay extends Thread {
	public MitiSay(String threadName) {
		super(threadName);
	}
	
	public void run() {
		System.out.println(getName() + " start ---  ");
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i + getName());
			
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(getName() + " end ---  ");

	}
}
