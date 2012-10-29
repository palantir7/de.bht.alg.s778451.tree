import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

public class DFS extends Thread {

	private static int vertexCount;
	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static Graphics g;
	private static int[][] pos;

	/**
	 * Running
	 * @param args Filepath as String 
	 */
	public static void run(String file) {
		// URL zu Zieldatei
		// String url =
		// "F:\\GitHub\\de.bht.alg.s778451.tree\\scr\\dat\\graph20.txt";
		String url = file;
		// Lade Graph der Zieldatei
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToGraph(url, false);
		DFS.graph = G;

		// derzeit deaktiviert !!!
		// readGraph(G);
	}

	private static enum VertexState {
		White, Gray, Black
	}

	/**
	 * liest den Graphen
	 * 
	 * @param G the @Graph
	 */
	public static void readGraph(@SuppressWarnings("rawtypes") Graph G, int startNode) {
		// Anzahl der Knoten
		vertexCount = G.getNumberVertices();

		// Setzte alle Vertizes auf Weiﬂ
		VertexState state[] = new VertexState[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			state[i] = VertexState.White;
			System.out.println("Node: " + i + " Color: WHITE");
		}
		loopDFS(startNode, state);
	}

	/**
	 * Iteriert ¸ber den Baum
	 * 
	 * @param u Parent-Node
	 * @param state Color of Node (State)
	 */
	private static void loopDFS(int u, VertexState[] state) {
		state[u] = VertexState.Gray;
		System.out.println("Node: " + u + " Color: GRAY");
		g.setColor(Color.GRAY);
		g.fillOval(pos[u][0], pos[u][1], 10, 10);

		for (int v = 0; v < vertexCount; v++) {
			if (isEdge(u, v) && state[v] == VertexState.White) {
				loopDFS(v, state);
			}
		}
		state[u] = VertexState.Black;
		System.out.println("Node: " + u + " Color: BLACK");
		g.setColor(Color.BLACK);
		g.fillOval(pos[u][0], pos[u][1], 10, 10);
	}

	/**
	 * Schaut ob Punkt u und Punkt v eine Kante haben
	 * 
	 * @param u Parent-Node
	 * @param v Childe-Node
	 * @return @boolean true or false
	 */
	private static boolean isEdge(int u, int v) {
		@SuppressWarnings("unchecked")
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (@SuppressWarnings("rawtypes")
		Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				// hier kann die GUI angeschlossen werden
				// hier wird bei true der Status von Weiﬂ zu Grau zu Schwarz
				
				// gibt die daten in der console aus
				//System.out.println("looking from " + u + " -- to --> " + v);
				return true;
			}
		}
		return false;
	}

	/**
	 * Graph Getter
	 * @return @Graph
	 */
	@SuppressWarnings("rawtypes")
	public static Graph getGraph() {
		return graph;
	}

	/**
	 * Position Getter
	 * @return @Position
	 */
	public static int[][] getPos() {
		return pos;
	}

	/**
	 * Position Setter
	 * @param pos Position-Array
	 */
	public static void setPos(int[][] pos) {
		DFS.pos = pos;
	}

	/**
	 * Graphics Getter
	 * @return @Graphics
	 */
	public static Graphics getG() {
		return g;
	}

	/**
	 * Graphics Setter
	 * @param g @Graphics
	 */
	public static void setG(Graphics g) {
		DFS.g = g;
	}

}
