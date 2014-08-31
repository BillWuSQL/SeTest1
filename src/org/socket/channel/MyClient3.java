package org.socket.channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < 100; i++) {  
//            final int idx = i;  
			final int idx = i;  
            new Thread(new MyRunnable(idx)).start();  
        }  

	}
	
	private static final  class MyRunnable implements Runnable {

		private  int idx;  
		  
        private MyRunnable(int idx) {  
            this.idx = idx;  
        }  
        
		@Override
		public void run() {
			// TODO Auto-generated method stub
            SocketChannel socketChannel = null;  
            try {
				socketChannel = SocketChannel.open();
				SocketAddress socketAddress = new InetSocketAddress("localhost", 10001);  
				socketChannel.connect(socketAddress);  
				
				MyRequestObject myRequestObject = new MyRequestObject("request_" + idx, "request_" );
				sendData(socketChannel, myRequestObject);  
				MyResponseObject myResponseObject = receiveData(socketChannel);  
				
//				socketChannel.close();  
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}  
		
	}

	private static void sendData(SocketChannel socketChannel, MyRequestObject myRequestObject) throws IOException {  
        byte[] bytes = SerializableUtil.toBytes(myRequestObject);  
        ByteBuffer buffer = ByteBuffer.wrap(bytes);  
        socketChannel.write(buffer);  
        socketChannel.socket().shutdownOutput();  
    }  
	
	private static MyResponseObject receiveData(SocketChannel socketChannel) throws IOException {  
		
		MyResponseObject myResponseObject = null;  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
        byte[] bytes;  
        int count = 0;  
        while ((count = socketChannel.read(buffer)) >= 0) {  
            buffer.flip();  
            bytes = new byte[count];  
            buffer.get(bytes);  
            baos.write(bytes);  
            buffer.clear();  
        }  
        bytes = baos.toByteArray();  
        Object obj = SerializableUtil.toObject(bytes);  
        myResponseObject = (MyResponseObject) obj;  
        socketChannel.socket().shutdownInput();  
//        baos.close();
        
        return myResponseObject;  
	}
	
	
}

