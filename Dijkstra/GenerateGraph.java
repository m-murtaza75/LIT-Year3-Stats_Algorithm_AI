/**
 *Student Number: K00223470
 *Student Name: Muhammad Murtaza
**/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.*;


public class GenerateGraph
{
	public static void main(String[]args) throws FileNotFoundException
	{

		 //Specify the number of NODES to be created
		 //Will be created from 0 to (specified range - 1)
		 int nodes = 3;

		 //Writing the FILE NAME
		 String outputFileName = Integer.toString(nodes) + "NodeGraphData.txt";


		 PrintWriter out = new PrintWriter(outputFileName);

		 //Flag Indicator (Using Stream of Arcs file type)
		 char fileFormat = 'S';

		 //Range for Source nodes
		 int minForStart = 0;
		 int maxForStart = nodes - 1;

		 //Range for destination nodes
		 int minForDest = 1;
		 int maxForDest = nodes - 1;

		 //Range for weight
		 int min = 4;
		 int max = 25;

		 String[] graphData = new String[nodes];

		 //Variable to store source,dest values into graphData array
		 String s_d;

		 String start;
		 String destination;
		 String weight;

		 int randomNumber;


		 out.println(fileFormat);
		 out.println(nodes);


		 randomNumber = (int)(Math.random()*((maxForStart-minForStart)+1))+minForStart;
		 start = Integer.toString(randomNumber);

		 randomNumber = (int)(Math.random()*((maxForDest-minForDest)+1))+minForDest;
		 destination = Integer.toString(randomNumber);

		 randomNumber = (int)(Math.random()*((max-min)+1))+min;
		 weight = Integer.toString(randomNumber);

		 s_d = start + "," + destination;


		 //
		 for (int i = 0; i < nodes; i++)
		 {
			if (graphData[i] == null)
			{
				graphData[i] = s_d;
				System.out.println(graphData[i] + "," + weight);
				out.println(graphData[i] + "," + weight);
				i = i - 1;


			}
			else if((s_d.equals(graphData[i]))||(start.equals(destination)))
			{
				//"\nDuplicate number detected";
				do
				{
					 randomNumber = (int)(Math.random()*((maxForStart-minForStart)+1))+minForStart;
					 start = Integer.toString(randomNumber);

					 randomNumber = (int)(Math.random()*((maxForDest-minForDest)+1))+minForDest;
					 destination = Integer.toString(randomNumber);

					 randomNumber = (int)(Math.random()*((max-min)+1))+min;
					 weight = Integer.toString(randomNumber);

		 			 s_d = start + "," + destination;

				}while(s_d.equals(graphData[i]));
				i = -1;
			}
		  }

		  out.close();
	}

}