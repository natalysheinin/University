	/********************************/
	/*			 AUTHOR:			*/
	/*		 NATALY SHEININ			*/
	/********************************/

	import java.io.*;
	import java.net.*;
	import java.util.*;

	public class Client2 {

		static volatile boolean check = true;
		static int comparable;

		//calendar variables
			static int year; static int month; static int day; static int hour; static int minute; static int second; 
			static Calendar attackTime;
			static Calendar date;


		//method establishes when time has been reached
		public static boolean checkTime(Calendar one, Calendar two)
		{
			comparable = one.compareTo(two);
			if (comparable == 0)
			{
				check = false;
				return true;

			}
			else
				return false;
		}

		public static void main(String[] args) throws UnknownHostException, IOException {

			//connection variables
			String hostName = "localhost";
			int portNumberC = 7705;
			int portNumberS = 7704;

			boolean conn = true;

			//waiting for connection from server
			try {
					//Acting a server, listening for "client" connections.
					ServerSocket s = new ServerSocket(portNumberC);
					Socket clientS = s.accept();

					PrintWriter out = new PrintWriter(clientS.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

					//recognize that Coordinator has connected.
					System.out.println(in.readLine());

					//getting current time variables
					date = Calendar.getInstance();
					year = date.get(Calendar.YEAR); 
					month = date.get(Calendar.MONTH);
					day = date.get(Calendar.DAY_OF_MONTH);
					
					//recieiving prefered attack time.
					out.println("Hello master, the current time is " + Calendar.getInstance().getTime());
					out.println("1. Please enter preferred hour to begin attack: ");
					hour = Integer.parseInt(in.readLine());
					out.println("2. Please enter preferred minute to begin attack: ");
					minute = Integer.parseInt(in.readLine());
					out.println("3. Please enter preferred second to begin attack: ");
					second = Integer.parseInt(in.readLine());

					attackTime = new GregorianCalendar(year, month, day, hour, minute, second);
					System.out.println("got attack time");	
					clientS.close();

				}
			catch (IOException e)
				{
					e.printStackTrace();
				}

				/*********************DONE BEHAVING AS SERVER, NOW WORKS AS CLIENT*****************/
				//loop keeps running until current time matches attackTime
					while (check)
					{
							attackTime = new GregorianCalendar(year, month, day, hour, minute, second);
							//date = Calendar.getInstance;
							boolean matching = checkTime(Calendar.getInstance(), attackTime);

							//ENTERS HERE ONLY WHEN TIME MATCHES
							if (matching)
							{
								System.out.println("reached time at: " + Calendar.getInstance().getTime());

								Socket attackS = new Socket(hostName, portNumberS);

								PrintWriter out = new PrintWriter(attackS.getOutputStream(), true);
								BufferedReader in = new BufferedReader(new InputStreamReader(attackS.getInputStream()));
								BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
								Scanner sc = new Scanner(System.in);

								System.out.println("i got here");
								while (conn)

								{
									out.println(sc.nextLine());
								}

							}
					}


			}
		}
