package org.thread.wait;

public class ThreadA {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ThreadB b = new ThreadB();
		new Thread(b).start();
		synchronized (b) {
			System.out.println("�ȴ�����b��ɼ��㡣����"); 
			b.wait(3000,999999);
		}
		System.out.println("b���������ܺ��ǣ�" + b.total); 
	}

}
