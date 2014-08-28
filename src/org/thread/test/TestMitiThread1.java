package org.thread.test;

import java.util.ArrayList;

public class TestMitiThread1 implements Runnable {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		
		TestMitiThread1 test = new TestMitiThread1();
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
//		th1.setPriority(Thread.MAX_PRIORITY);
		th1.start();
//		th1.setDaemon(true);
		th1.setPriority(9);
		System.out.println("th1是否后台线程: ============= " + th1.isDaemon());
		System.out.println("th1线程优先级: ============= " + th1.getPriority());
//		th2.setDaemon(true);
		th2.start();
		System.out.println("th2是否后台线程: ============= " + th2.isDaemon());
		System.out.println("th2线程优先级: ============= " + th2.getPriority());


		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
		ArrayList sa;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		for (int i = 0; i < 10; i++) {
//			System.out.println();
//			System.out.println();
//			System.out.println();
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
