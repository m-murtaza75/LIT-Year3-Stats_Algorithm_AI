/**
 *Student Number: K00223470
 *Student Name: Muhammad Murtaza
**/


import java.util.*;
import java.io.*;

class Vertex implements Comparable<Vertex>
{
  public final String name;
	public ArrayList<Edge> neighbours;
	public LinkedList<Vertex> path;
	public int minDistance = Integer.MAX_VALUE;
	public Vertex previous;

	public int compareTo(Vertex other)
	{
		return Integer.compare(minDistance,other.minDistance);
	}
	public Vertex(String name)
	{
		this.name = name;
		neighbours = new ArrayList<Edge>();
		path = new LinkedList<Vertex>();
	}
	public String toString()
	{
		return name;
	}
}



class Graph
{
  private ArrayList<Vertex> vertices;
	public Graph(int numberVertices)
	{
		vertices = new ArrayList<Vertex>(numberVertices);
		for(int i=0;i<numberVertices;i++)
		{
			vertices.add(new Vertex(Integer.toString(i)));
		}
	}

	public void addEdge(int src, int dest, int weight)
	{
		Vertex s = vertices.get(src);
		Edge new_edge = new Edge(vertices.get(dest),weight);
		s.neighbours.add(new_edge);
	}

	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}

	public Vertex getVertex(int vert)
	{
		return vertices.get(vert);
	}
}




//To represent the edges in the graph.
class Edge
{
  public final Vertex target;
	public final int weight;
	public Edge(Vertex target, int weight)
	{
		this.target = target;
		this.weight = weight;
	}
}



public class Dijkstra
{

	private static Scanner input;

  public static void main(String[] args)
  {
	  	String fileFormat = "";
		int nodes;
		String data = "";

		int rootNode;

		int source;
		int dest;
		int weight;


		Dijkstra obj = new Dijkstra();

		if(args.length == 0)
		{
		            System.out.println("File name not specified.");
		            System.exit(1);
        }

		try
		{
		       File file = new File(args[0]);
		       input = new Scanner(file);
		}
		catch (IOException ioException)
		{
		            System.err.println("Error Cannot open file. Check file name");
		            System.exit(1);
        }


		fileFormat = input.next();
		nodes = Integer.parseInt(input.next());

		if((!fileFormat.contains("S"))||(nodes <= 0))
		{
			 System.err.println("Error! Invalid Graph data\n");
			 System.exit(1);

		}

		System.out.println("\nFile format: " + fileFormat + " (Stream of arcs)\n");
		System.out.println("Nodes:	" + nodes);


		Scanner in = new Scanner(System.in);

		System.out.print("\nUser! Please specify the Root node (Between 0 to " + (nodes - 1) + "): ");
		rootNode = in.nextInt();
		System.out.println("\nUser choosed: " + rootNode);

		if((rootNode < 0)||(rootNode >= nodes))
		{
					 System.err.println("Error! Invalid root-node selection\n");
					 System.exit(1);
		}

		Graph g = new Graph(nodes);

		String[] lineReader;


		while (input.hasNext())
		{
			data = input.next();

			lineReader = data.split(",");

			source = Integer.parseInt(lineReader[0]);
			dest = Integer.parseInt(lineReader[1]);
			weight = Integer.parseInt(lineReader[2]);

			if((source < 0)||(dest < 0)||(weight < 0))
			{
				System.err.println("Error! negative value/values detected in graph file.\n");
				System.exit(1);
			}


			g.addEdge(source,dest,weight);

		}

		// Calculate Dijkstra by specifing Root Node.
		obj.calculate(g.getVertex(rootNode));

		// Print the minimum Distance.
		for(Vertex v:g.getVertices())
		{
			//System.out.print("Vertex - "+v+" , Dist - "+ v.minDistance+" , Path - ");

			System.out.print("Vertex,				Dist From Root, 		Path\n");
			System.out.print(v+"\t\t\t\t," + v.minDistance + "\t\t\t\t,");

			for(Vertex pathvert:v.path)
			{
				System.out.print(pathvert+" ");
			}
			System.out.println(""+v);
		}

	}

	public void calculate(Vertex source)
	{
		// Algorithm steps:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.

		source.minDistance = 0;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);

		while(!queue.isEmpty())
		{

			Vertex u = queue.poll();

			for(Edge neighbour:u.neighbours)
			{
				int newDist = u.minDistance+neighbour.weight;

				if(neighbour.target.minDistance>newDist)
				{
					// Remove the node from the queue to update the distance value.
					queue.remove(neighbour.target);
					neighbour.target.minDistance = newDist;

					// Take the path visited till now and add the new node.s
					neighbour.target.path = new LinkedList<Vertex>(u.path);
					neighbour.target.path.add(u);

					//Reenter the node with new distance.
					queue.add(neighbour.target);
				}
			}
		}
	}

}