package org.thread.bank;

public class Test {

	public static void main(String[] args) {
		User2 u = new User2("张三", 100); 
		// TODO Auto-generated method stub
		MyThread m1 = new MyThread(u,10);
		MyThread m2 = new MyThread(u,10);
		MyThread m3 = new MyThread(u,10);
		MyThread m4 = new MyThread(u,10);
		MyThread m5 = new MyThread(u,10);
		MyThread m6 = new MyThread(u,10);
		MyThread m7 = new MyThread(u,10);

		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		new Thread(m4).start();
		new Thread(m5).start();
		new Thread(m6).start();
		new Thread(m7).start();
	}

}


class MyThread implements Runnable {
	
	private User2 user;
	private int y = 0; 
	
	MyThread(User2 u, int y) {
        this.user = u;
        this.y = y;
	} 
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		user.oper(y);
	}
	
}


class User2 {
	private String code;
    private int cash;

    User2(String code, int cash) {
            this.code = code;
            this.cash = cash;
    } 
    
    public String getCode() {
    	return code;
	}
	
	public void setCode(String code) {
	        this.code = code;
	}

	
	public synchronized void oper(int x) {
		try {
			Thread.sleep(10L);
			this.cash += x;
			System.out.println(Thread.currentThread().getName() + "运行结束了，增加“" + x + "”，当前用户账户余额为：" + cash); 
			Thread.sleep(10L); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User{ code = " + code + " , cash = " + cash + " }" ;
	}

}