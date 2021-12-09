package main;

import java.awt.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class SingleServer {
	private ServerSocket server;
	Vector nowClient = new Vector(); 
	Random rnd = new Random();		
	
	public SingleServer() {}
	void start()
	{
		try
		{
			server = new ServerSocket(1234);
			System.out.println("The server has been executed.");
			
			while(true)
			{
				Socket socket = server.accept();
				System.out.println("It's connected to the user.");
				
				new RSPThread(socket).start();		
				
				nowClient.add(socket);
				
				System.out.println("The user : " + nowClient.size());
				
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String [] args)
	{
		SingleServer server = new SingleServer();
		server.start();
	}
	
	class RSPThread extends Thread
	{
		Socket socket;
		
		private DataInputStream reader;
		private DataOutputStream writer;
		
		RSPThread(Socket socket)
		{
			this.socket = socket;
		}
		
		public void run()
		{
			try
			{
				reader = new DataInputStream(socket.getInputStream());
				writer = new DataOutputStream(socket.getOutputStream());
				String msg;
				
				while((msg=reader.readUTF())!= null)
				{
					if(msg.equals("OK"))
					{
						writer.writeInt((rnd.nextInt(3)+1)*256);
						writer.flush();
					}
				}
			}
			catch(IOException e){}
			
			finally
			{
				try
				{
					nowClient.remove(socket);
					
					if(reader != null)
					{
						reader.close();
					}
					
					if(writer != null)
					{
						writer.close();
					}

					if(socket != null)
					{
						socket.close();
					}
					
					reader = null; writer = null; socket = null;
					
					System.out.println("Left user.");
					System.out.println("Current User : " + nowClient.size());
				}
				catch(IOException e) {}
			}
		}
	}

}
