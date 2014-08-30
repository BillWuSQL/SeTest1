package org.socket.muti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(10001);
		
		while (true) {
			Socket socket = server.accept();
			invoke(socket);
		}
	}
	
	public static void invoke(final Socket client) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BufferedReader in = null;  
                PrintWriter out = null;  
                
                try {
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					out = new PrintWriter(client.getOutputStream());
					
					while (true) {
						String msg = in.readLine();
						System.out.println(msg);  
						out.println("Server received " + msg);
						out.flush();  
						if (msg.equals("bye")) {  
                            break;  
                        }
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						in.close();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}).start();
	}

}
