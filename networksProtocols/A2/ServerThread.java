/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/*		  ASSIGNMENT 2    		*/
/********************************/
/********************************/

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;


public class ServerThread extends Thread {
	
	MainServer ms = new MainServer();
	String s;
	volatile boolean conn = true;

	//instantializing Socket
	private Socket socket = null;
	String sNumber;


	public ServerThread(Socket socket) {

		super("ServerThread");
		this.socket = socket;		

	}

//method that creates thread, implementing the Runnable interface.
	public void run()  {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			//Welcoming Client Connection & Waiing for Authentication by Student #
			out.println("Welcome client");

			while(conn)
			{
				s = in.readLine();
				System.out.println(s);

			}	

		socket.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


