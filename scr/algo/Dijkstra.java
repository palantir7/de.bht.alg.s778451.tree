/**
 * 
 */
package algo;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;
import paint.PaintPanel;

/**
 * @author Marcel Buchmann (s778451)
 * @email marcel.buchmann(- at -)googlemail.com
 * @version 1.0.0
 * @date 11.11.2012
 * @project de.bht.alg.s778451.tree
 *
 */
public class Dijkstra {

	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static PaintPanel paintArea;
	private static int[][] pos;

	/**
	 * Running (Initialization)
	 * 
	 * @param args
	 *            Filepath as String
	 */
	public static void run(String file, PaintPanel panel) {
		// Set PaintPanel
		paintArea = panel;
		// URL to Targetfile
		String url = file;
		// Generate Graph of Targetfile
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToWeightedGraph(url, false);
		Dijkstra.setGraph(G);

		for (int i = 0; i < graph.getNumberVertices(); i++) {
			for (int j = 0; j < graph.getNumberVertices(); j++) {
				isEdgeInit(i, j);
			}

			System.out.println(graph.getIncidentEdges(i));
//			[(0,1; g:1), (0,2; g:4)]
//			[(1,0; g:1), (1,3; g:4), (1,2; g:2), (1,4; g:1)]
//			[(2,0; g:4), (2,1; g:2), (2,5; g:1)]
//			[(3,1; g:4), (3,6; g:1), (3,5; g:4)]
//			[(4,1; g:1), (4,5; g:1)]
//			[(5,2; g:1), (5,4; g:1), (5,6; g:4), (5,3; g:4)]
//			[(6,3; g:1), (6,5; g:4)]
		}

		// (only for tests in use) !!!
		int startNode = 0;
		readGraph(G, startNode);
	}

	/**
	 * Read the Graph (First Iteration of Tree)
	 * 
	 * @param G
	 *            the @Graph
	 */
	@SuppressWarnings({ "rawtypes" })
	public static void readGraph(Graph G, int startNode) {

		Queue<Integer> theQueue = new LinkedList<Integer>();

		int[] parent = new int[graph.getNumberVertices()];
		int[][] dist = new int[graph.getNumberVertices()][1];

		for (int i = 0; i < graph.getNumberVertices(); i++) {

			((PaintPanel) paintArea).addNode("WHITE", i);
			((PaintPanel) paintArea).addText("" + i, "BLACK", i);

			parent[i] = -1;
		}

		boolean[] identified = new boolean[graph.getNumberVertices()];
		identified[startNode] = true;

		((PaintPanel) paintArea).addNode("GRAY", startNode);

		theQueue.offer(startNode);

		while (!theQueue.isEmpty()) {
			int currentNode = theQueue.remove();
			Iterator itr = graph.getNeighbours(currentNode).iterator();

			dist[currentNode][0]++;
			isEdge(startNode, currentNode);

			while (itr.hasNext()) {
				int nextNode = Integer.parseInt(itr.next().toString());

				if (!identified[nextNode]) {
					isEdge(currentNode, nextNode);

					identified[nextNode] = true;

					((PaintPanel) paintArea).addNode("GRAY", nextNode);

					theQueue.offer(nextNode);
					parent[nextNode] = currentNode;
					dist[nextNode][0] = dist[nextNode][0]
							+ dist[currentNode][0];
				}

				((PaintPanel) paintArea).addNode("BLACK", currentNode);
				((PaintPanel) paintArea).addText("   Dist: "
						+ (dist[currentNode][0] - 1), "BLACK", currentNode);
			}
		}
		// return parent;
	}

	/**
	 * Looking at Point 'u' and Point 'v' whether they have an Edge
	 * 
	 * @param u
	 *            Parent-Node
	 * @param v
	 *            Neighbor-Node
	 * @return @boolean true or false
	 */
	private static boolean isEdgeInit(int u, int v) {
		@SuppressWarnings("unchecked")
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (@SuppressWarnings("rawtypes")
		Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				((PaintPanel) paintArea).addEdge("GRAY", u, v);
				return true;
			}
		}
		return false;
	}

	/**
	 * Looking at Point 'u' and Point 'v' whether they have an Edge
	 * 
	 * @param u
	 *            Parent-Node
	 * @param v
	 *            Neighbor-Node
	 * @return @boolean true or false
	 */
	private static boolean isEdge(int u, int v) {
		@SuppressWarnings("unchecked")
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (@SuppressWarnings("rawtypes")
		Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				((PaintPanel) paintArea).addEdge("RED", u, v);
				return true;
			}
		}
		return false;
	}

	// ---------- Getten & Setter ---------->>>

	/**
	 * Graph Getter
	 * 
	 * @return @Graph
	 */
	@SuppressWarnings("rawtypes")
	public static Graph getGraph() {
		return graph;
	}

	/**
	 * Graph Setter
	 * 
	 * @param g
	 * @Graph
	 */
	@SuppressWarnings("rawtypes")
	public static void setGraph(Graph g) {
		Dijkstra.graph = g;
	}

	/**
	 * Position Getter
	 * 
	 * @return @Position
	 */
	public static int[][] getPos() {
		return pos;
	}

	/**
	 * Position Setter
	 * 
	 * @param pos
	 *            Position-Array
	 */
	public static void setPos(int[][] pos) {
		Dijkstra.pos = pos;
	}

}
