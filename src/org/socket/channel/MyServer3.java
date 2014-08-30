package org.socket.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import javax.swing.text.html.HTMLDocument.Iterator;

public class MyServer3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);  
		serverSocketChannel.socket().bind(new InetSocketAddress(10001));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while (selector.select() > 0) {
//			Iterator<SelectionKey> it = selector.selectedKeys().iterator();  
//			while (it.hasNext()) {  
//                SelectionKey readyKey = it.next();  
//                it.remove();  
////                execute((ServerSocketChannel) readyKey.channel());  
//            }  
		}
		
	}

}
