import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner sc = new Scanner(System.in);

		// Read the number of testcases to follow
		int t = sc.nextInt();

		// Iterate over the testcases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}
	}

	public static void testCase(Scanner sc) {

		int n = sc.nextInt();
		int m = sc.nextInt();
		int BF = sc.nextInt();

		Graph graph = new Graph(n);
		for (int i = 0; i < m; i++) {
			graph.addEdge(sc.nextInt(), sc.nextInt());
		}

		//Woohoo we have an undirected graph

		//is bipartite? returns Array = {0} if not, returns array with coloring if yes
		int[] colors = isBipartite(graph);
		int[] meh = { 0 };
		if (colors.length == n) {
			int BFcolor = colors[BF];
			for (int i = 0; i < colors.length; i++) {
				if (colors[i] == BFcolor) {
					System.out.print(i + " ");
				}
			}

			System.out.println();

		} else {
			System.out.println("no");
		}
	}

	public static int[] isBipartite(Graph graph) {

		//Colors of the vertices
		int red = -1;
		int blue = -2;
		int[] colors = new int[graph.V];

		//Queue for BFS
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0); //random start vertex maybe?

		colors[0] = red; //start vertex is red

		while (!queue.isEmpty()) {

			int current = queue.remove();
			for (Integer adjacentVertex : graph.adj[current]) {

				//vertex was not yet discovered
				if (colors[adjacentVertex] == 0) {
					//color it the other color
					if (colors[current] == red) {
						colors[adjacentVertex] = blue;
					} else if (colors[current] == blue) {
						colors[adjacentVertex] = red;
					}
					queue.add(adjacentVertex);

					//they have the same color, not bipartite!	
				} else if (colors[current] == colors[adjacentVertex]) {
					int[] meh = { 0 };
					return meh;
				}

			}
		}

		return colors;
	}
}

class Graph {
	int V;
	LinkedList<Integer> adj[];

	//Constructor: base Array is List, its elements are Lists
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	//makes undirected Graph
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
}
