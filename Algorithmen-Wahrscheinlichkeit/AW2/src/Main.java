import java.util.*;

class Main {
	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner scanner = new Scanner(System.in);

		// Read the number of testcases to follow
		int t = scanner.nextInt();

		// Iterate over the testcases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(scanner);
		}
	}

	public static void testCase(Scanner scanner) {

		// Read the number of vertices and edges
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int i;

		int sum = 0;

		//List of Edges {u,v} with weight w
		List<Edge> edges = new ArrayList<Edge>();

		for (i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();

			edges.add(new Edge(u, v, w));

		}

		//Ascending Edges
		Comparator<Edge> comparator = new SomeComparator<Edge>();
		Collections.sort(edges, comparator);

		//Array of nodes, initialized, each is their own parent
		Node[] vertices = new Node[n];
		for (i = 0; i < vertices.length; i++) {
			vertices[i] = new Node();
		}

		//Go through all Edges
		for (Edge e : edges) {

			if (vertices[e.u].getParent() != vertices[e.v].getParent()) {
				vertices[e.v].setParent(vertices[e.u].getParent());
				sum = sum + e.getWeight();

			}

		}

		System.out.println(sum);
	}

}

class Edge {
	public int u;
	public int v;
	private int w;

	public Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public int getWeight() {
		return w;
	}

}

class SomeComparator<E> implements Comparator<Edge> {

	public int compare(Edge e1, Edge e2) {
		return e1.getWeight() - e2.getWeight(); //Idk what calculation should be done here but lets keep it that way

	}
}

class Node {
	public int number;
	public Node parent;

	public Node(int number) {
		this.number = number;
	}

	public Node getParent() {
		if (parent == this) {
			return this;
		}

		return parent.getParent();

	}

	public void setParent(Node newParent) {
		Node old = getParent();
		old.parent = newParent;
	}

	public Node() {
		parent = this;
	}

}
