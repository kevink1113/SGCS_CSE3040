package cse3040fp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;
		
		
		ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
		        this.name = name;
		    } catch(Exception e) {}
		} 
		
		@SuppressWarnings("all") 
		public void run() {
			Scanner scanner = new Scanner(System.in); 
			try {
				if (out != null) { 
					out.writeUTF(name);
				}
				
				
				while (out != null) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.print(name + ">> ");
						
						String str = scanner.nextLine();
						str = str.trim();
						if(str.equals("add") || str.equals("borrow") || str.equals("return") 
								|| str.equals("info") || str.equals("search"))
							out.writeUTF(str); 
						else {
							System.out.println("[available commands]");
							System.out.println("add: add a new book to the list of books.");
							System.out.println("borrow: borrow a book from the library.");
							System.out.println("return: return a book to the library.");
							System.out.println("info: show list of books I am currently borrowing.");
							System.out.println("search: search for books.");
						}
						
						
						if(str.equals("add")) {
							System.out.print("add-title> ");
							String addTitle = scanner.nextLine().trim();
							out.writeUTF(addTitle);
							
							if(addTitle.length() == 0)
								out.writeUTF("");
							else {
								System.out.print("add-author> ");
								out.writeUTF(scanner.nextLine().trim());
							}
						}
						else if(str.equals("borrow")) {
							System.out.print("borrow-title> ");
							out.writeUTF(scanner.nextLine().trim());
						}
						else if (str.equals("return")) {
							System.out.print("return-title> ");
							out.writeUTF(scanner.nextLine().trim());
						}
						else if (str.equals("info")) {
							
						}
						else if(str.equals("search")) {
							while(true) {
								System.out.print("search-string> ");
								String searchTitle = scanner.nextLine();
								
								if(searchTitle.length() >=3 || searchTitle.length() == 0) {
									out.writeUTF(searchTitle);
									break;
								} else {
									System.out.println("Search string must be longer than 2 characters.");
								}
								
							}
						}
					
					
				}
			} catch(IOException e) {}
		}
	}
	
	static class ClientReceiver extends Thread { 
		Socket socket;
	    DataInputStream in;
	    
	    ClientReceiver(Socket socket) { 
	    	this.socket = socket;
	    	try {
	    		in = new DataInputStream(socket.getInputStream()); 
	    	} catch(IOException e) {}
	    }
	    
	    public void run() {
	        while (in != null) {
	        	try { 
	        		System.out.println(in.readUTF());
	            } catch(IOException e) {}
	        }
	    }
	}
	
	@SuppressWarnings("all") 
	public static void main(String args[]) { 
		if(args.length != 2) {
			System.out.println("Please give the IP address and port number as arguments.");
			System.exit(0);
		}
		try {
			String serverIp = args[0];
			Socket socket = new Socket(serverIp, Integer.parseInt(args[1])); 
			System.out.println("connected to server.");
			
			
			String name = "";
			Scanner nameScan = new Scanner(System.in);
			while(true) {
				System.out.print("Enter userID>> ");
				name = nameScan.nextLine();
				
				Boolean rightName = true;
				
				if(name.contains(" ")) rightName = false;
				if(name.length() == 0) rightName = false;
				for(int i = 0; i< name.length(); i++) {
					if( !(('a' <= name.charAt(i) && name.charAt(i) <= 'z') || ('A'<=name.charAt(i) && name.charAt(i) <= 'Z') || ('0'<=name.charAt(i) && name.charAt(i) <= '9')) ) {
						rightName = false;
						// System.out.println("not true becuse of " + name.charAt(i));
					}
				}	
				if( rightName == false ) {
					System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
				}
				else  {
					break;
				}
			}
			
			System.out.println("Hello " + name + "!");
			
			Thread sender = new Thread(new ClientSender(socket, name)); 
			Thread receiver = new Thread(new ClientReceiver(socket)); 
			sender.start();
			receiver.start();
		} catch(ConnectException ce) {
			System.out.println("Connection establishment failed.");
		} catch(Exception e) {
			System.out.println("Connection establishment failed.");
		} 
	}

}
