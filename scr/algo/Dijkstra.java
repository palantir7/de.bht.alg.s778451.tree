/**
 * 
 */
package algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
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
public class Dijkstra implements Comparable<Vertex> {

	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static PaintPanel paintArea;
	private static int[][] pos;
	@SuppressWarnings("unused")
	private static String[][] tbl;
	@SuppressWarnings("unused")
	private static int tblSize;

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
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToWeightedGraph(url,
				false);

		System.out.println("Running Dijkstra ... with "
				+ G.getVertices().size() + " Vertices...");

		// Zeichne Knoten ein
		for (Vertex v : G.getVertices()) {
			((PaintPanel) paintArea).addNode("WHITE", v.getId());
		}

		Dijkstra.setGraph(G);

		// (only for tests in use) !!!
		Vertex startNode = G.getVertex(3);

		readGraph(G, startNode);

		for (Vertex v : G.getVertices()) {
			int dist = (int) v.minDistance;

			System.out.println("Kürzester Weg von " + startNode.getId() + " zu " + v.getId());
			System.out.println("Pfad: " + getShortestPathTo(v) + " Distanz: " + dist);

			((PaintPanel) paintArea).addNode("BLACK", v.getId());
			((PaintPanel) paintArea).addText("   Dist: " + dist, "BLACK",
					v.getId());
		}
	}

	/**
	 * Read the Graph (Iteration of Graph)
	 * 
	 * @param G
	 *            Graph
	 * @param start
	 *            Startnode
	 * @param stop
	 *            Stopnode
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readGraph(Graph G, Vertex start) {

		((PaintPanel) paintArea).addNode("RED", start.getId());
		((PaintPanel) paintArea).addText("" + start.getId(), "BLACK",
				start.getId());

		start.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);

		@SuppressWarnings("unused")
		int count = 0;

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			Collection<Edge> edges = G.getEdges();

			// Zeichne Bennenung und Initnode des aktuellen Knotens
			((PaintPanel) paintArea)
					.addText("" + u.getId(), "BLACK", u.getId());
			((PaintPanel) paintArea).addNode("WHITE", start.getId());

			// Konsolenausgabe für die aktuelle Runde
			count++;
			System.out.println("Calculate ... Rounts of Node " + u.getId());

			// Schaut sich die Kanten zu verbundenen Knoten an
			for (Edge edge : edges) {
				// holt isch von der Kante den anderen Vertex
				Vertex v = edge.getVertexB();

				// besorgt sich die Daten der Kante
				double weight = edge.getWeight();
				double distanceThroughU = u.minDistance + weight;

				// Zeige Änderungen in der Grafik an
				((PaintPanel) paintArea).addNode("GRAY", u.getId());
				isEdgeInit(u.getId(), v.getId());
				((PaintPanel) paintArea).addNode("RED", u.getId());

				// schaut ob die Distanz sich verringert
				if (distanceThroughU < v.minDistance) {
					// Stellt die geänderte Kante da
					isEdgeChange(u.getId(), v.getId());
					// entfernt alten Vertex
					vertexQueue.remove(v);
					// setzt die neue Distanz für dieses Vertex
					v.minDistance = distanceThroughU;
					// setzt Vorgängervertex
					v.previous = u;
					// Added den geänderten Vertex in die Queue
					vertexQueue.add(v);
				} else if (distanceThroughU == v.minDistance) {
					// färbe die Kante wenn sich nichts ändert
					isEdgeInit(v.getId(), u.getId());
				}
			}
		}
	}

	/**
	 * Shortest Path to Vertex
	 * @param target
	 *            is a @Vertex
	 * @return List of @Vertices
	 */
	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
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
	@SuppressWarnings("unused")
	private static boolean isEdge(int u, int v) {
		@SuppressWarnings("unchecked")
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (@SuppressWarnings("rawtypes")
		Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				((PaintPanel) paintArea).addEdge("BLACK", u, v);
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
	private static boolean isEdgeChange(int u, int v) {
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

	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
