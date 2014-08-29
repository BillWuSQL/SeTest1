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
 * 仓库
 * @author Administrator
 * 
 */
class GitHub {
	public final int maxStockSize = 100;
	public int stockCounts;	//当前库存
	
	GitHub(int stockCounts) {
		this.stockCounts = stockCounts;
	}
	
	/**
	 * 生产产品
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
		System.out.println("已经生产了" + produceCounnts + "个产品，现仓储量为" + stockCounts); 
		notifyAll(); 
		
	}
	
	/**
	 * 消费产品
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
		System.out.println("已经消费了" + consumeCounnts + "个产品，现仓储量为" + stockCounts); 
		notifyAll(); 
	}
}



/**
 * 生产者
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
 * 消费者
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




