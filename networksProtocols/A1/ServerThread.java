/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/********************************/

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;


public class ServerThread extends Thread {
	
	MainServer ms = new MainServer();
	int n;
	String choice;

	HashMap<String, String> answers =  new HashMap<String, String>();

	//instantializing Socket
	private Socket socket = null;
	String sNumber;

	//List to store the student #s from the textfile.
	String[] numbers = new String[100];

	public ServerThread(Socket socket) {

		super("ServerThread");
		this.socket = socket;		

	}
	/******************* FILE FUNCTIONALITY ***************************************/

	public void readFile()
	{	
		int x = 0;
		try
        {
        	//HARDCODED FILENAME.
        	x`
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
	}

	public boolean authenticate(String studentNumber)
	{	
		//boolean q = ms.checkStudent(studentNumber);
		//if (q)
		//	return false;
		//else {
				for(int i=0; i<10; i++){ 
            		if (studentNumber.equals(numbers[i]))
            		{ 	
            			return true;
           		 } 
      	     }
    		
    		return false;
				//}
	}
	/******************* FILE FUNCTIONALITY ***************************************/

//method that creates thread, implementing the Runnable interface.
	public void run()  {

		try {

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		//Read in File
		readFile();

		//Welcoming Client Connection & Waiing for Authentication by Student #
		out.println("Welcome. Please authenticate yourself with a student number");

		//Incomming Student # from Client
		sNumber = in.readLine();

		/***************** AUTHENTICATION PROCESS ****************************/
		//boolean authentication = authenticate(sNumber);

		if (authenticate(sNumber))
		{
			out.println("Authentication passed.");
			/***************** NUMVER OF CHOICES PORTION *****************************/
		n = ms.getNumberOfChoices();
		out.println("Please enter your number of choice, based on a,b,c, etc out of " + n + " choices.");

		//Catches User's choice
		choice = in.readLine();

		//Records choice in HashMap using given method from MainServer
		ms.recordAnswer(sNumber, choice);
		socket.close();
		/***************** END NUMBER OF CHOICES PORTION **************************/
		}
		
		//AUTHETICATION FAILED
		else
		{
			out.println("FAIL");
			out.println("Authentican failed, goodbye.");
			
			//closing TCP connection with the client.
			socket.close();
		}
		/***************** END AUTHENTICATION PROCESS ****************************/
		

		

		/*while ((line = in.readLine()) != null) {

			System.out.println("Client says: " + line);
			out.println(line);
		}
			socket.close();*/
	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


