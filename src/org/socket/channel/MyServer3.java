package org.socket.channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


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
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();  
			while (it.hasNext()) {  
                SelectionKey readyKey = it.next();  
                it.remove();  
                execute((ServerSocketChannel) readyKey.channel());  
            }  
          }
	}
	
	
	public static void execute(ServerSocketChannel serverSocketChannel) throws IOException  {
		SocketChannel socketChannel = null;  
		socketChannel = serverSocketChannel.accept();  
		
		MyRequestObject myRequestObject = receiveData(socketChannel);  
		MyResponseObject myResponseObject = new MyResponseObject(  
                "response for " + myRequestObject.getName(),   
                "response for " + myRequestObject.getValue());  
        sendData(socketChannel, myResponseObject);  
		
	}
	
	private static MyRequestObject receiveData(SocketChannel socketChannel) throws IOException {
		MyRequestObject myRequestObject = null;  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		ByteBuffer buffer = ByteBuffer.allocate(1024);  
		
		byte[] bytes;  
		int size = 0;  
		while ((size = socketChannel.read(buffer)) >= 0) {
            buffer.flip();  
            bytes = new byte[size];  
            buffer.get(bytes);  
            baos.write(bytes);  
            buffer.clear();  
        }  
		bytes = baos.toByteArray();  
        Object obj = SerializableUtil.toObject(bytes);  
        myRequestObject = (MyRequestObject)obj;  
//        socketChannel.socket().shutdownInput();
//        baos.close();  
        return myRequestObject;  
	}
	
	private static void sendData(SocketChannel socketChannel, MyResponseObject myResponseObject) throws IOException {  
        byte[] bytes = SerializableUtil.toBytes(myResponseObject);  
        ByteBuffer buffer = ByteBuffer.wrap(bytes);  
        socketChannel.write(buffer);  
    }  
		
}

