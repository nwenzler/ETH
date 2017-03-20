import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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

		//Adjacency list in form of a Map
		HashMap<String, ArrayList<String>> adjacency = new HashMap<String, ArrayList<String>>();
		//Maps for the node degrees
		HashMap<String, Integer> in_degree = new HashMap<String, Integer>();
		HashMap<String, Integer> out_degree = new HashMap<String, Integer>();

		for (int i = 0; i < n; i++) {
			String snippet = sc.next(); //abc
			String node_one = snippet.substring(0, 2); //ab
			String node_two = snippet.substring(1, 3); //bc

			//Initialize the two degree hash maps
			if (!out_degree.containsKey(node_one) && !in_degree.containsKey(node_one)) {
				out_degree.put(node_one, 0);
				in_degree.put(node_one, 0);
			}
			if (!node_one.equals(node_two) && !out_degree.containsKey(node_two) && !in_degree.containsKey(node_two)) {
				out_degree.put(node_two, 0);
				in_degree.put(node_two, 0);
			}

			//node_one ist schon enthalten: mache node_two in die Liste von one und erstelle two, falls noch nicht
			if (adjacency.containsKey(node_one) && !node_one.equals(node_two)) {
				adjacency.get(node_one).add(node_two);

				out_degree.put(node_one, out_degree.get(node_one) + 1);

				in_degree.put(node_two, in_degree.get(node_two) + 1);

				if (!adjacency.containsKey(node_two)) {
					adjacency.put(node_two, new ArrayList<String>());
				}

			} else {
				adjacency.put(node_one, new ArrayList<String>());

				if (!node_one.equals(node_two) && !adjacency.containsKey(node_two)) {
					adjacency.put(node_two, new ArrayList<String>());
					adjacency.get(node_one).add(node_two);

					out_degree.put(node_one, out_degree.get(node_one) + 1);

					in_degree.put(node_two, in_degree.get(node_two) + 1);
				} else if (!node_one.equals(node_two) && adjacency.containsKey(node_two)) {
					adjacency.get(node_one).add(node_two);

					out_degree.put(node_one, out_degree.get(node_one) + 1);

					in_degree.put(node_two, in_degree.get(node_two) + 1);
				}

			}

		} //Yay, we have three maps: adjacency, in_deg and out_deg of each node!

		/*System.out.println(adjacency);
		System.out.println(in_degree);
		System.out.println(out_degree);*/

		int starts = 0;
		int ends = 0;
		int zeros = 0;
		int hasSameDegree = 0;

		Set set = adjacency.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String node = (String) entry.getKey();

			if (out_degree.get(node) == in_degree.get(node) + 1) {
				starts++;
			} else if (in_degree.get(node) == out_degree.get(node) + 1) {
				ends++;
			} else if (out_degree.get(node) == 0 && in_degree.get(node) == 0) {
				zeros++;
			}

			if ((out_degree.get(node) - in_degree.get(node)) == 0) {
				hasSameDegree++;
			}
		}

		/*System.out.println(starts);
		System.out.println(ends);
		System.out.println(zeros);*/

		if (starts == 1 && ends == 1 && hasSameDegree == adjacency.size() - 2) {
			System.out.println("yes");
		} else if (starts > 1 || ends > 1) {
			System.out.println("no");
		} else if (starts == 0 && ends == 0 && adjacency.size() > 1) {
			System.out.println("no");
		} else if (starts == 0 && ends == 0 && adjacency.size() < 2) {
			System.out.println("yes");
		}

	}

}
