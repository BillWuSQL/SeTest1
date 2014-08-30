package org.socket.sendthing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    
    public static void main(String[] args) throws IOException {  
        ServerSocket server = new ServerSocket(10001);  
  
        while (true) {  
            Socket socket = server.accept();  
            socket.setSoTimeout(12);
            invoke(socket);  
        }  
    }  
  
    private static void invoke(final Socket socket) throws IOException {  
        new Thread(new Runnable() {  
            public void run() {  
                ObjectInputStream is = null;  
                ObjectOutputStream os = null;  
                try {  
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));  
                    os = new ObjectOutputStream(socket.getOutputStream());  
  
                    Object obj = is.readObject();  
                    User user = (User)obj;  
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());  
  
                    user.setName(user.getName() + "_new");  
                    user.setPassword(user.getPassword() + "_new");  
  
                    os.writeObject(user);  
                    os.flush();  
                } catch (IOException ex) {  
                } catch(ClassNotFoundException ex) {  
                } finally {  
                    try {  
                        is.close();  
                    } catch(Exception ex) {}  
                    try {  
                        os.close();  
                    } catch(Exception ex) {}  
                    try {  
                        socket.close();  
                    } catch(Exception ex) {}  
                }  
            }  
        }).start();  
    }  
    }
