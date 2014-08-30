package org.socket.muti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket  = new Socket("localhost", 10001);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		while (true) {
			String msg = reader.readLine();
			out.println(msg);
			out.flush();
			
			if (msg.equals("bye")) {
				break;
			}
			System.out.println(in.readLine());  
		}
		
		socket.close();  
		
		
		
	}
	
}
