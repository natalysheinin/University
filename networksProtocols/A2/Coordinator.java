/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/*		  ASSIGNMENT 2    		*/
/********************************/
/********************************/

	import java.io.*;
	import java.net.*;
	import java.util.*;

	/*The Coordinator behaves as a client, connecting to the Client.java 
	when he wants to communicate.
	Mainly, to communicate the decided time of attack. */
	public class Coordinator{


	public static void main(String[] args) throws UnknownHostException, IOException{	

		String hostName = "localhost";
		int portNumber = 7705;


				Socket clientS = new Socket(hostName, portNumber);

				PrintWriter out = new PrintWriter(clientS.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				Scanner sc = new Scanner(System.in);


				System.out.println("Coordinator connected to client.");
				out.println("you're my slave now");


				//SENDING ATTACK INFORMATION TIME TO CLIENT
				System.out.println(in.readLine());
				System.out.println(in.readLine());
				int h = sc.nextInt();
				out.println(h);
				System.out.println(in.readLine());
				int m = sc.nextInt();
				out.println(m);
				System.out.println(in.readLine());
				int s = sc.nextInt();
				out.println(s);

	}
}

