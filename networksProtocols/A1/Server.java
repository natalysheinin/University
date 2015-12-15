/********************************/
/*			 AUTHOR:			*/
/*		 NATALY SHEININ			*/
/********************************/

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {
		
		/***************************** INITIALIZATIONS *****************************/
		String line;
		BufferedReader in;
		PrintWriter out;
		boolean conn = true;
		int portNumber = 7704;
		
		/***************************** END INITIALIZATIONS **************************/
		
		// Initializing a ServerSocket listening on the portNumber
		ServerSocket s = new ServerSocket(portNumber);
   

   		// Listening for connection and accepts it.
		Socket clientS = s.accept();
		
		// Initializing input & output streams to communicate to client
		in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
		out = new PrintWriter(clientS.getOutputStream(), true);

		/************************** INITIAL COMMUNICATIONS **************************/
		//System.out.println("Server up & running...");
		out.println("Hi client... server is up & running now");
		//System.out.println("getInetAddress: " + clientS.getInetAddress());
		//System.out.println("getLocalAddress: " + clientS.getLocalAddress());
		//System.out.println("getLocalPort: " + clientS.getLocalPort());

		/************************ END INITIAL COMMUNICATIONS ************************/
		 
		 while (conn)
		 {
			 line = in.readLine();
			 // out.println(line);

			 if (line.equals("STOP"))
			 {

			 	out.println("Connection closed");
			 	conn = false;
			 	clientS.close();

			 }

			 else
			 
			 	out.println(line);
				System.out.println("Client says: " + line);

		 }

		
	}

}