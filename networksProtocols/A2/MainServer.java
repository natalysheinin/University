/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/*		  ASSIGNMENT 2    		*/
/********************************/

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Calendar;

public class MainServer implements Runnable {

		//volatile and static variable to work as a flag for synchronization witht
		static volatile boolean conn = true;
		static volatile boolean shutdown = false;
		int portNumber = 7704;
		int counter = 0;

		//get Calendar instance
    Calendar now = Calendar.getInstance();

	public void run()
	{
			while (!shutdown)
			{
			try {
			// Initializing a ServerSocket listening on the portNumber
			ServerSocket s = new ServerSocket(portNumber);

				
			 	while (conn)
		 	 	{
		 			new ServerThread(s.accept()).start();
		 			counter++;
			 		System.out.println("Accepted connection #" + counter + " at time: " + System.currentTimeMillis());

		 	 	}
		 	 }

		 	catch (IOException e)
			{
				e.printStackTrace();
			}
		}
			/*try {Thread.sleep(3500);
			System.out.println("Hello from thread");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}	
			*/
	}
	
	public static void main(String[] args) throws IOException 
	{

		//WELCOMEING SERVER AND LISTING INSTRUCTIONS
		System.out.println("Welcome to the SERVER.");
		System.out.println();

		//LISTENING TO INPUT
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext())
		{
			String i = sc.nextLine();
			//System.out.println(i); 

			if (i.equals("start") || i.equals("START"))
			{
				System.out.println("server started");
				new Thread(new MainServer()).start();
			}	

		}

	}
}
