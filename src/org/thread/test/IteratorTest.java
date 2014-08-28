package org.thread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List list = new ArrayList();
		List list = new LinkedList();
		int len = 100000;
		init(list,len);
		tesLoop(list,len);
		tesItera(list,len);
	}
	
	
	public static void init(List list, int n) {
		for (int i = 0; i < n; i++) {
            list.add(i);  
        }
	}
	
	public static void tesLoop(List list, int n) {
		long starttime = 0;  
        long endtime = 0;  
        starttime = System.currentTimeMillis();  
//        for (int count = 0; count <= n; count++) {  
            for (int i = 0; i < list.size(); i++) {  
                list.get(i);  
            }  
//        }  
        endtime = System.currentTimeMillis();  
        System.out.println("tesLoop一共花了" + (endtime - starttime) + "ms时间"); 
	}
	
	public static void tesItera(List list, int n) {
		long starttime = 0;  
        long endtime = 0;  
        starttime = System.currentTimeMillis();  
//        for (int count = 0; count <= n; count++) {  
            for (Iterator itr = list.iterator(); itr.hasNext();) {  
                itr.next();  
            }  
//        }  
        endtime = System.currentTimeMillis();  
        System.out.println("tesItera一共花了" + (endtime - starttime) + "ms时间"); 
	}


}
