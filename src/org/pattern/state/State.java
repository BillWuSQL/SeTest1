package org.pattern.state;

public interface State {

	/** 
     * Ͷ��Ӳ�� 
     */  
    void insertQuarter();  
    /** 
     * ����ҡ�����������ҡ����������ش��������ͷ��ǹ� 
     */  
    void ejectQuarter();  
    /** 
     * ת��ҡ�� 
     */  
    void turnCrank();  
    /** 
     * �����ų��ǹ�����������ڲ�״̬�����س�ʼ��Ͷ��״̬ 
     */  
    void dispense();  
	
}
