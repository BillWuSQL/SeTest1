package org.thread.test;

import java.util.ArrayList;

public class TestMitiThread1 implements Runnable {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		TestMitiThread1 test = new TestMitiThread1();
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		th1.start();
		th2.start();
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
		ArrayList sa;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
	}

}
