/* Authors: Nataly & Mike */
/* Date: February 24th, 2016 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class  A{

	public static void main(String[] args) throws IOException {
		
		// SET UP FILE - words in Eclipse as well, use: "/bin/in.txt"
		FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") + "/in.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		
		//VARIABLES
		int m;
		int n;
		
		String current_word;
		int form = -1;
		
		//ENDINGS
		String[] endings = { "sse", "s", "st", "le", "l", "lt", "ks", "ni", "na", "ta", "ga" };

		
		//read # of lines of dictionary
		strLine = br.readLine();
		n = Integer.parseInt(strLine);
		//n = Integer.parseInt(strLine);
		
		//this is our dictionary
		String[][] words = new String[n][15];
		
		/****************** POPULATING DICTIONARY ********************/
		for (int i=0; i < n; i++)
		{
			strLine = br.readLine();
			String[] tokens = strLine.split(" ");
			
			for (int j=0; j< 4; j++)
			{
				words[i][j] = tokens[j];
			
			}
		}
		
		for (int i=0; i < n; i++)
		{
			for (int j=4; j < 15; j++)
			{
				int z = j-4;
				
				words[i][j] = (words[i][2]).concat(endings[z]);
				
			}
		}
		/****************** POPULATING DICTIONARY COMPLETE ********************/
		
		/****************** WORD MATCHING *************************************/
		//read # of input words to find in dictionary
		strLine = br.readLine();
		m = Integer.parseInt(strLine);
		
		//for every word
		for (int k=0; k < m; k++)
		{
			boolean match = false;
			String definition = "UNKNOWN";
			current_word = br.readLine();
			for (int i=0; i < n; i++)
			{	
				
				
				for (int j=1; j < 15; j++)
				{
					if (current_word.equals(words[i][j]))
					{
						definition = words[i][0];
						form = j;
						match = true;
						System.out.println(current_word + " " + definition + " " + j);
						
					}
					
					//NO MATCH FOUND & SEARCHED EVERYTHING
					if (i == (n-1) && j == 14 && match == false)
					{
					System.out.println(current_word + " " + definition);
					}
					
					
				}			
			}
		}
		/****************** WORD MATCHING COMPLETE *******************************/
				
		//method to print out 2D array
		//System.out.println(Arrays.deepToString(words));
		
		//Close the input stream
		br.close();
	}

}
