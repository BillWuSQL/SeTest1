package org.thread.produceQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GitHut s = new GitHut();
		
//		System.out.println((int)(Math.random()*1000));
		
		Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(p);
        //service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);
        
//        service.shutdown();
		
	}

}


class GitHut {
	
	BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);
	
	public void push(Product p) {
		try {
			queues.put(p);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Product pull() throws InterruptedException {
		return queues.take();
	}
	
	
}


class Product {
	private int id;

    public Product(int id) {
        this.id = id;
    }

    public String toString() {
        return "产品：" + String.valueOf(this.id);
    }
}



class Producer implements Runnable  {

	private String name;
	private GitHut githut;
	public Producer(String name, GitHut githut) {
		this.name = name;
		this.githut = githut;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Product pro = new Product((int)(Math.random()*1000));
			githut.push(pro);
			System.out.println(name + "已生产(" + pro.toString() + ").");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


class Consumer implements Runnable  {

	private String name;
	private GitHut githut;
	public Consumer(String name, GitHut githut) {
		this.name = name;
		this.githut = githut;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			try {
				Product product = githut.pull();
				System.out.println(name + "已消费(" + product.toString() + ").");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}