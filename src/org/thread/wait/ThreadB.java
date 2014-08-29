package org.thread.wait;

public class ThreadB implements Runnable {
	int total;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
//			this.notify();
			for (int i = 0; i < 101; i++) {
				total += i;
			}
		}
	}

}
