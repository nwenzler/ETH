
import java.util.Scanner;
import java.util.*;

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

		Graph g = new Graph(n);

		for (int i = 0; i < m; i++) {
			int s = sc.nextInt();
			int t = sc.nextInt();

			g.addEdge(s, t);
		}
		
		g.DFS();

	}

}


class Graph {
	int n;

	
	LinkedList<Integer> adj[];
	int time = 0;
	static final int noParents = -1;

	// Constructor
	Graph(int v) {
		n = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v); 
	}

	
	void DFS_rec(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {

		//children in DFS Tree
		int children = 0;
		visited[u] = true;
		disc[u] = low[u] = ++time;

		
		Iterator<Integer> i = adj[u].iterator();
		while (i.hasNext()) {
			int v = i.next();

			
			if (!visited[v]) {
				children++;
				parent[v] = u;
				DFS_rec(v, visited, disc, low, parent, ap);

				low[u] = Math.min(low[u], low[v]);

				//root is articulation point
				if (parent[u] == noParents && children > 1)
					ap[u] = true;

				//second case of articulation point
				if (parent[u] != noParents && low[v] >= disc[u])
					ap[u] = true;
			}

			
			else if (v != parent[u])
				low[u] = Math.min(low[u], disc[v]);
		}
	}

	
	void DFS() {

		int count = 0;
		
		boolean visited[] = new boolean[n];
		int disc[] = new int[n];
		int low[] = new int[n];
		int parent[] = new int[n];
		boolean ap[] = new boolean[n]; // To store articulation points

		
		for (int i = 0; i < n; i++) {
			parent[i] = noParents;
			visited[i] = false;
			ap[i] = false;
		}

		
		for (int i = 0; i < n; i++)
			if (visited[i] == false)
				DFS_rec(i, visited, disc, low, parent, ap);

		
		for (int i = 0; i < n; i++) {
			if (ap[i] == true) {
				
				System.out.print(i + " ");
			} else {
				count++;
			}
			
			if (count == n) {
				
				System.out.print(-1); //no articulation points
			}
		} if(count !=0){
			System.out.println();
		}
	
	}
}