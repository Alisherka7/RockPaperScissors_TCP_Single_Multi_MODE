package main;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiServer {
	
	// ��Ʈ
	private static Integer port = 1340;

	// search empty port
	private static boolean validPort(Integer x) {
		return x >= 1 && x <= 65535 ? true : false;
	}
	private static int getPort() 
	{

		Integer input = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("포트 주소를 입력하세or\n");
			System.out.print("POrt (" + MultiServer.port + "): ");
			input = sc.nextInt();

		} while (input != 0 && !MultiServer.validPort(input));
		sc.close();

		return input == 0 ? MultiServer.port : input;
	}
	
	public void serverstart() throws Exception 
	{
		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;
               
		// Set port
		MultiServer.port = getPort();

		// Create new server socket
		ServerSocket welcomeSocket = new ServerSocket(MultiServer.port);
		System.out.println("\nport " + welcomeSocket.getLocalPort() + " ...");

		while (!welcomeSocket.isClosed()) {
			// Player one
			Socket client_1 = welcomeSocket.accept();
			if (client_1.isConnected()) {
				System.out.println("\nPlayer one (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ") ... waiting for player two ...");
			}
			DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
			BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

			// Player two
			Socket client_2 = welcomeSocket.accept();
			if (client_2.isConnected()) {
				System.out.println("Player two (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ")  ... lets start ...");
			}
			DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
			BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

			// Get client inputs
			inputClient_1 = inClient_1.readLine();
			inputClient_2 = inClient_2.readLine();


			// if same options
			if (inputClient_1.equals(inputClient_2)) {
				resClient_1 = "Draw";
				resClient_2 = "Draw";
				System.out.println("It's a draw.");
			}

			else if (inputClient_1.equals("R") && inputClient_2.equals("S")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");

			}

			else if (inputClient_1.equals("S") && inputClient_2.equals("R")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			else if (inputClient_1.equals("R") && inputClient_2.equals("P")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			else if (inputClient_1.equals("P") && inputClient_2.equals("R")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}

			else if (inputClient_1.equals("S") && inputClient_2.equals("P")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}

			else if (inputClient_1.equals("P") && inputClient_2.equals("S")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			// Send responses in uppercase and close sockets
			outClient_1.writeBytes(resClient_1.toUpperCase());
			outClient_2.writeBytes(resClient_2.toUpperCase());
			client_1.close();
			client_2.close();

			System.out.println("\nWaiting for new players ...\n");

		}
	}
	
	public static void main(String args[]) throws Exception 
	{
		MultiServer multiserver = new MultiServer();
		multiserver.serverstart();
	}
}
