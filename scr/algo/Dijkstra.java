/**
 * 
 */
package algo;

import java.util.Collection;
import java.util.Iterator;
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
public class Dijkstra {

	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static PaintPanel paintArea;
	private static int[][] pos;
	private static String[][] tbl;
	private static int tblSize;

	/**
	 * Running (Initialization)
	 * 
	 * @param args
	 *            Filepath as String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void run(String file, PaintPanel panel) {
		// Set PaintPanel
		paintArea = panel;
		// URL to Targetfile
		String url = file;
		// Generate Graph of Targetfile
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToWeightedGraph(url,
				false);
		Dijkstra.setGraph(G);

		// Zeichne Kanten
		for (int i = 0; i < graph.getNumberVertices(); i++) {
			for (int j = 0; j < graph.getNumberVertices(); j++) {
				isEdgeInit(i, j);
			}
		}

		// Initialisierung
		int count = 0;
		tbl = new String[graph.getEdges().size()][3];
		tblSize = graph.getEdges().size();

		// Iteriere über Kanten und sammel Daten in tbl
		Iterator<Edge> i = graph.getEdges().iterator();
		while (i.hasNext()) {
			Edge x = i.next();
			tbl[count][0] = x.getVertexA().toString(); // Vertex A
			tbl[count][1] = x.getVertexB().toString(); // Vertex B
			tbl[count][2] = "" + x.getWeight(); // Weight
			count++;
		}

		// (only for tests in use) !!!
		Vertex startNode = G.getVertex(0);
		Vertex stopNode = G.getVertex(6);
		readGraph(G, startNode, stopNode);
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
	public static void readGraph(Graph G, Vertex start, Vertex stop) {

		// Priority-Queue zum Verwalten der Laenge des kuerzesten Weges bis zum
		// Knoten
		PriorityQueue<Vertex> pQ = new PriorityQueue<Vertex>();

		Collection<Vertex> V = G.getVertices();
		// fuer jeden Knoten
		for (Vertex v : V) {
			// Entfernung ist unendlich
			v.setDist(Double.MAX_VALUE);
			// Knoten noch nicht gesehen
			v.setSeen(false);
			// Vorgaenger noch nicht ermittelt
			v.setPrev(null);
		}

		// endgueltige Kosten zum Startknoten
		start.setDist(0);
		// erster Eintrag in PriorityQueue
		pQ.add(start);

		// solange noch Eintraege in Priority-Queue
		while (!pQ.isEmpty()) {

			// billigster Eintrag in PriorityQueue
			Vertex v = pQ.poll();
			// falls schon bearbeitet: ignorieren
			if (v.isSeen()) continue;
			// als bearbeitet markieren
			v.setSeen(true);

			Collection<Vertex> edges = G.getNeighbours(v);
			System.out.println(edges);

			// fuer jede Nachbarkante e von v tue
			for (Vertex e : edges) {
				// besorge Zielknoten w
				Vertex w = e;
				// initalisiert die Distanz auf 0
				int dist = 0;
				// sucht in der tbl nach den Knotenpaar v & w und deren Distanz
				for (int i = 0; i < tblSize; i++) {
					if (tbl[i][0].matches("" + v.getId())
							&& tbl[i][1].equals("" + w.getId())) {
						dist = Integer.parseInt(tbl[i][2]);
					} else if (tbl[i][0].matches("" + w.getId())
							&& tbl[i][1].matches("" + v.getId())) {
						dist = Integer.parseInt(tbl[i][2]);
					}
				}
				// besorge Kosten c zum Zielknoten w
				double c = dist;
				// falls Kantenkosten negativ
				if (c < 0) throw new
				// melde Fehler
				RuntimeException("Negativ");
				// falls Verkuerzung moeglich
				double newDist = v.getDist() + c;
				if (w.getDist() > newDist) {
					// setze neue Verkuerzung
					w.setDist(newDist);
					// notiere verursachenden Vorgaenger
					w.setPrev(v);
					// neuer Eintrag in PriorityQueue
					pQ.add(w);
					System.out.println(w);
				}
			}
		}
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
