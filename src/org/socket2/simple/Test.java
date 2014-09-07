package org.socket2.simple;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeMap;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Set s;c
			TreeMap mm;
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			if (interfaceList == null) {
				System.out.println("not interface ");
			} else {
				while (interfaceList.hasMoreElements()) {
					NetworkInterface iface = interfaceList.nextElement();
					System.out.println("inteface " + iface.getName());
					Enumeration<InetAddress> addrList = iface.getInetAddresses();
					if (!addrList.hasMoreElements()) {
						System.out.println("no addr ");
					}
					while (addrList.hasMoreElements()) {
						InetAddress address = addrList.nextElement();
						System.out.print("Address == " + (address instanceof Inet4Address ? "(v4)" : 
							(address instanceof Inet6Address ? "(v6)" : "(?)")));
						System.out.println(": " + address.getHostAddress());
					}
				}
			}
			 
			
			for (String host : args) {
				InetAddress.getAllByName(host);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
