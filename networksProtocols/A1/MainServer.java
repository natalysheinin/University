		/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/*		  ASSIGNMENT 2    		*/
/********************************/

import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer implements Runnable {

		static boolean conn = true;
		int portNumber = 7704;
		int counter = 0;

		//number of choices
		static int n;

		//hasmap to store answers for LIST functionality
		static HashMap<String, String> answers =  new HashMap<String, String>();

	//volatile and static variable to work as a flag for synchronization witht
	// the runnable thread and the END_QUESTION instruction.

	static volatile boolean shutdown = false;

	//Single Thread that gets created solely for the purpose of handling the 
	// START_QUESTION instruction.
	public void run()
	{
		
		while (!shutdown)
		{

			try {
			// Initializing a ServerSocket listening on the portNumber
			ServerSocket s = new ServerSocket(portNumber);

				//
			 	while (conn)
		 	 	{
		 			new ServerThread(s.accept()).start();
		 			counter++;
			 		System.out.println("Accepted connection #" + counter);

		 	 	}
		 	 }

		 	catch (IOException e)
			{
				e.printStackTrace();
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
			
	}
	public void recordAnswer(String sn, String answ)
	{
		answers.put(sn, answ);
	}

	public int getNumberOfChoices()
	{
		return n;
	}

	public boolean checkStudent(String sn)
	{
		return answers.containsKey(sn);
	}

	public static void main(String[] args) throws IOException {

	//WELCOMEING SERVER AND LISTING INSTRUCTIONS
	System.out.println("Welcome to the SERVER. You can run any of the following instructions to complete your daily server routines.");
		System.out.println();
		System.out.println("1. START_QUESTION(n): starts the server process that runs with n choices.");
		System.out.println("2. END_QUESTION(): Terminate the server process; students can no longer send responses.");
		System.out.println("3. LIST: Lists students who sent answers.");
		System.out.println("Please enter START_QUESTION, END_QUESTION, or LIST to continue");
		System.out.println();
	
	//LISTENING TO INPUT
	Scanner sc = new Scanner(System.in);
	while (sc.hasNext())
	{
    	String i = sc.nextLine();
    	//System.out.println(i);

    	//DEALING WITH THE START_QUESTION
    	if (i.equals("START_QUESTION"))
    	{
    		//CLEAR THE HASHMAP IF THERE WAS A QUESTION BEFORE
    		answers.clear();

  			System.out.println("Please enter the number of choices (2,3,4,5).");
  			n = sc.nextInt();
  			(new Thread(new MainServer())).start();

    	}

    	//DEALING WITH THE END_QUESTION
    	if (i.equals("END_QUESTION"))
    	{	
    		System.out.println("End question");
    		//terminate the runnable thread above.
    		conn = false;
    		shutdown = true;

    	}
    	if (i.equals("LIST"))
			{
				System.out.println("The List");
				System.out.println(answers.toString());

				
			}
	}

}
}
