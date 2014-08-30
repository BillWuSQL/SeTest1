package org.thread.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BlockingDeque bDeque = new LinkedBlockingDeque(20); 
		BlockingQueue bqueue = new ArrayBlockingQueue(20); 
		
		// TODO Auto-generated method stub
		MyCount myCount = new MyCount("95599200901215522", 10000); 
//		Lock lock = new ReentrantLock();
		ReadWriteLock  lock = new ReentrantReadWriteLock(false);
        ExecutorService pool = Executors.newCachedThreadPool();
        //����һЩ���������û���һ�����ÿ�����Ĵ棬ȡ��ȡ�������ְ�
        User u1 = new User("����", myCount, -4000, lock, false);
        User u2 = new User("��������", myCount, 6000, lock, false);
        User u3 = new User("��������", myCount, -8000, lock, false);
        User u4 = new User("����", myCount, 800, lock, false); 
        
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4); 
        
        pool.shutdown(); 
	}

}

class User implements Runnable {

	private String name;                //�û���
    private MyCount myCount;        //��Ҫ�������˻�
    private int iocash;                 //�����Ľ���Ȼ������֮����
    private ReadWriteLock  myLock;                //ִ�в��������������
    private boolean ischeck;        
    
    User(String name, MyCount myCount, int iocash, ReadWriteLock  myLock, boolean ischeck) {
        this.name = name;
        this.myCount = myCount;
        this.iocash = iocash;
        this.myLock = myLock;
        this.ischeck = ischeck; 
    }
    
    

    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (ischeck) {
	            //��ȡ����
	            myLock.readLock().lock();
	            System.out.println("����" + name + "���ڲ�ѯ" + myCount + "�˻�����ǰ���Ϊ" + myCount.getCash());
	            //�ͷŶ���
	            myLock.readLock().unlock();
	    } else {
	            //��ȡд��
	            myLock.writeLock().lock();
	            //ִ���ֽ�ҵ��
	            System.out.println("д��" + name + "���ڲ���" + myCount + "�˻������Ϊ" + iocash + "����ǰ���Ϊ" + myCount.getCash());
	            myCount.setCash(myCount.getCash() + iocash);
	            System.out.println("д��" + name + "����" + myCount + "�˻��ɹ������Ϊ" + iocash + "����ǰ���Ϊ" + myCount.getCash());
	            //�ͷ�д��
	            myLock.writeLock().unlock();
	    } 
	}
	
}


class MyCount {
	private String oid;         //�˺�
	private int cash;             //�˻����
	
	@Override
    public String toString() {
            return "MyCount{" +
                            "oid='" + oid + '\'' +
                            ", cash=" + cash +
                            '}';
    } 
	
    public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}


    MyCount(String oid, int cash) {
            this.oid = oid;
            this.cash = cash;
    } 
}
