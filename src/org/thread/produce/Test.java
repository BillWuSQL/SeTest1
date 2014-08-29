package org.thread.produce;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GitHub github = new GitHub(10);
		
		Consumer c1 = new Consumer(github, 10); 
		Consumer c2 = new Consumer(github, 10); 
		Consumer c3 = new Consumer(github, 10); 
		Consumer c4 = new Consumer(github, 10); 
		Consumer c5 = new Consumer(github, 10); 
		
		Producer p1 = new Producer(github, 10); 
		Producer p2 = new Producer(github, 10); 
		Producer p3 = new Producer(github, 10); 
		Producer p4 = new Producer(github, 10); 
		Producer p5 = new Producer(github, 10); 
		
		
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		
		
	}

}


/**
 * �ֿ�
 * @author Administrator
 * 
 */
class GitHub {
	public final int maxStockSize = 100;
	public int stockCounts;	//��ǰ���
	
	GitHub(int stockCounts) {
		this.stockCounts = stockCounts;
	}
	
	/**
	 * ������Ʒ
	 */
	public synchronized void produce(int produceCounnts) {
		while (produceCounnts + stockCounts > maxStockSize) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		stockCounts += produceCounnts ;
		System.out.println("�Ѿ�������" + produceCounnts + "����Ʒ���ֲִ���Ϊ" + stockCounts); 
		notifyAll(); 
		
	}
	
	/**
	 * ���Ѳ�Ʒ
	 */
	public synchronized void consume(int consumeCounnts) {
		while (stockCounts < consumeCounnts) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		stockCounts -= consumeCounnts ;
		System.out.println("�Ѿ�������" + consumeCounnts + "����Ʒ���ֲִ���Ϊ" + stockCounts); 
		notifyAll(); 
	}
}



/**
 * ������
 * @author Administrator
 *
 */
class Producer extends Thread  {
	
	private GitHub gitHub;
	private int produceCounnts;
	Producer(GitHub gitHub, int produceCounnts) {
		this.gitHub = gitHub;
		this.produceCounnts = produceCounnts;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gitHub.produce(produceCounnts);
	}
	
	
	
}

/**
 * ������
 * @author Administrator
 *
 */
class Consumer extends Thread   {
	
	private GitHub gitHub;
	private int consumeCounnts;
	Consumer(GitHub gitHub, int consumeCounnts) {
		this.gitHub = gitHub;
		this.consumeCounnts = consumeCounnts;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gitHub.consume(consumeCounnts);
	}
	
	
}




