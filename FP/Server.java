package cse3040fp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;


class BookInfo implements Comparable<BookInfo> {
	private String title = "";
	private String author = "";
	private String ID = "";
	
	BookInfo(String title, String author, String ID) {
		this.title = title;
		this.author = author;
		this.ID = ID;
	}
	
	public String getTitle() { return this.title; }
	public String getAuthor() { return this.author; }
	public String getID() { return this.ID; }
	public void setID(String ID) { this.ID = ID; }
	
	@Override
	public int compareTo(BookInfo e) {
		return this.title.toLowerCase().compareTo(e.title.toLowerCase());
	}
	
	public String toString() { 
		return "title: " + this.title + "\nauthor: " + this.author + "\nID: " + this.ID + "\n"; 
	}
	
}

class BookInfoReader {
	public static ArrayList<BookInfo> readBooks(String file) {
		ArrayList<BookInfo> arr = new ArrayList<BookInfo>();
		
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			return null;
		}
		
		while(input.hasNextLine()) {
			String in = input.nextLine();
			String[] temp;
			String delimiter = "\t";
			temp = in.split(delimiter);
			String title = temp[0];
			String author = temp[1];
			String ID = temp[2];
			
			arr.add(new BookInfo(title, author, ID));
		}
		
		return arr;
	}
}


public class Server {
	HashMap<String,DataOutputStream> clients;
	static ArrayList<BookInfo> list;
	
	Server() {
		clients = new HashMap<>();
		Collections.synchronizedMap(clients);
	}
	
	public void start(int port) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("server has started.");
			while(true) {
				socket = serverSocket.accept();
				System.out.println("a new connection from [" + socket.getInetAddress() + ":" + socket.getPort() + "]");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void sendTo(String msg, DataOutputStream out) {
		try {
			out.writeUTF(msg);
		} catch(Exception e) {
			System.out.println("Unable to find / send to the user matched to that ID.");
		}
	}
	
	static void sortPrint()  {
		Collections.sort(list);
		Iterator<BookInfo> it = list.iterator();
		while(it.hasNext()) System.out.println(it.next());
		
		
		PrintWriter pw;
		try {
			pw = new PrintWriter("books.txt");
			for(int i = 0; i < list.size(); i++)  {
				pw.println(list.get(i).getTitle() + "\t" + list.get(i).getAuthor() + "\t" + list.get(i).getID());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: Unable to wite data.");
		}
	}
	
	public static void main(String[] args) {
		
		if(args.length != 1) { // Lecture25 p.9
			System.out.println("Please give the port number as an argument.");
			System.exit(0);
		}
		
		list = BookInfoReader.readBooks("books.txt");
		if(list == null) {
			System.out.println("Input file not found.");
			return;
		}
		
		sortPrint();
		
		new Server().start(Integer.parseInt(args[0]));
		
		sortPrint();
	}
	
	class ServerReceiver extends Thread {  
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket socket) { 
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream()); 
			} catch(IOException e) {}
		}
	
		public void run() {
	        String name = "";
			try {
				name = in.readUTF();
				clients.put(name, out);
				
				sortPrint();
				System.out.println("Current number of users: " + clients.size()); 
				
				while (in != null) {
					String input = in.readUTF();
					
					if(input.equals("add")) {
						String newTitle = "";
						newTitle = in.readUTF();
						String newAuthor = "";
						newAuthor =in.readUTF();
						
						int searchIdx = -1;
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getTitle().toLowerCase().equals(newTitle.toLowerCase()))
								searchIdx = i;
						}
						if(searchIdx >=0) sendTo("The book already exists in the list.", out);
						else if(newTitle.length()==0 || newAuthor.length()==0) 
							System.out.println("input cancelled.");
						else {
							sendTo("A new book added to the list.", out);
							System.out.println("========= New Book =========");
							System.out.println("title : " + newTitle);
							System.out.println("author : " + newAuthor);
							System.out.println("ID : -");
							System.out.println("============================");
							list.add(new BookInfo(newTitle, newAuthor, "-"));
							sortPrint();
						}
						
					} 
					else if(input.equals("borrow")) {
						String borrowTitle = in.readUTF();
						
						int searchIdx = -1;
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getTitle().toLowerCase().equals(borrowTitle.toLowerCase()))
								searchIdx = i;
						}
						if(borrowTitle.length()==0)
							System.out.println("input cancelled.");
						else if(searchIdx < 0) 
							sendTo("The book is not available", out);
						else if (!list.get(searchIdx).getID().equals("-"))
							sendTo("The book is not available", out);
						else {
							sendTo("You borrowed a book. - " + list.get(searchIdx).getTitle(), out);
							list.get(searchIdx).setID(name);
							sortPrint();
							
							System.out.println("======= Borrowed Book ======");
							System.out.println("title : " + borrowTitle);
							// System.out.println("author : " + in.readUTF());
							System.out.println("ID : " + name);
							System.out.println("============================");
						}
						
					}
					else if (input.equals("return")) {
						String returnTitle = in.readUTF();
						
						int searchIdx = -1;
						for(int i =0; i < list.size(); i++) {
							if(list.get(i).getTitle().toLowerCase().equals(returnTitle.toLowerCase())
									&& list.get(i).getID().equals(name))
								searchIdx = i;
						}
						if(returnTitle.length()==0)
							System.out.println("input cancelled.");
						else if (searchIdx < 0) 
							sendTo("You did not borrow the book.", out);
						else {
							sendTo("You returned a book. - " + list.get(searchIdx).getTitle(), out);
							list.get(searchIdx).setID("-");
							sortPrint();
							
							System.out.println("======= Returned Book ======");
							System.out.println("title : " + returnTitle);
							// System.out.println("author : " + in.readUTF());
							System.out.println("ID : " + name);
							System.out.println("============================");
						}
						
					}
					else if(input.equals("info")) {
						Vector<String> infoList = new Vector<String>();
						
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getID().equals(name))
								infoList.add(list.get(i).getTitle() + ", " + list.get(i).getAuthor());
						}
						sendTo("You are currently borrowing " + infoList.size() + " books:", out);
						for(int i = 0; i < infoList.size(); i++) {
							sendTo(i+1 + ". " + infoList.get(i), out);
						}
						
					}
					else if (input.equals("search")) {
						String searchTitle = in.readUTF();
						Vector<String> searchList = new Vector<String>();
						
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getTitle().toLowerCase().contains(searchTitle.toLowerCase())
									|| list.get(i).getAuthor().toLowerCase().contains(searchTitle.toLowerCase()))
								searchList.add(list.get(i).getTitle() + ", " + list.get(i).getAuthor());
						}
						
						if(searchTitle.length() ==0) 
							System.out.println("input cancelled.");
						else if (searchTitle.length() <=2 )
							sendTo("Search string must be longer than 2 characters.", out);
						else {
							sendTo("Your search matched " + searchList.size() + " results.", out);
							for(int i = 0; i < searchList.size(); i++) {
								sendTo(i+1 + ". " + searchList.get(i), out);
							}
						}
						
					}
					
					// sendToAll(in.readUTF(), out); 
					
				}
				
			} catch(IOException e) {
				// ignore
			} finally {
			    // sendToAll("#"+name+" has left.");
			    clients.remove(name); 
			    System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+ " (" + name + ") ]"+" has disconnected."); 
			    System.out.println("Current number of users: " + clients.size());
			} 
		}
	}

}
