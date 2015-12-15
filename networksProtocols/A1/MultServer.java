/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/********************************/

import java.io.*;
import java.net.*;
import java.util.*;

public class MultServer {


	public static void main(String[] args) throws IOException {
		
		boolean conn = true;
		int portNumber = 7704;
		int counter = 0;

		String[] numbers = new String[100];

		/******************* FILE FUNCTIONALITY ***************************************/
		
			int x = 0;
			try
        	{
        		
        	//HARDCODED FILENAME.
        	FileReader fin = new FileReader("Students.txt");
        	Scanner src = new Scanner(fin);
			src.useDelimiter(" ");

			while (src.hasNext()) 
			{
				numbers[x] = src.next();
				//System.out.println(numbers[x]);
				x++;
			}
    		}	
    		catch (IOException e)
			{
				e.printStackTrace();
			}	

	/******************* FILE FUNCTIONALITY ***************************************/

		// Initializing a ServerSocket listening on the portNumber
		ServerSocket s = new ServerSocket(portNumber);

		 // Loops forever listening for client connection requests, accepts a connection 
		 // and creates a new thread which it hands the socket returned from the accept.
		System.out.println("Welcome to the SERVER. You can run any of the following instructions to complete your daily server routines.");
		System.out.println();
		System.out.println("1. START_QUESTION(n): starts the server process that runs with n choices.");
		System.out.println("2. END_QUESTION(): Terminate the server process; students can no longer send responses.");
		System.out.println("3. LIST: Lists students who sent answers.");
		System.out.println();

		try 
		{

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String str = in.readLine();

			if (str.equals("START_QUESTION"))
			{
				 while (conn)
		 	 	{
		 			new ServerThread(s.accept()).start();
		 			counter++;
			 		System.out.println("Accepted connection #" + counter);

		 	 	}
			}

			if (str.equals("LIST"))
			{
					

			}


		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		
	}

}