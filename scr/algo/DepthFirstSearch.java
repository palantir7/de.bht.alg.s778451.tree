package algo;

import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

public class DepthFirstSearch extends Thread {

	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static Graphics graphics;
	private static int[][] pos;
	private static int vertexCount;
	
	private static int x;
	private static int y;
	
	/**
	 * Mainmethode - ONLY for Tests
	 * @param args not in use !!!
	 */
	public static void main(String[] args) {
		String file = "F:\\GitHub\\de.bht.alg.s778451.tree\\scr\\dat\\graph20.txt";
		DepthFirstSearch.run(file);
	}

	/**
	 * Running (Initialization)
	 * 
	 * @param args
	 *            Filepath as String
	 */
	public static void run(String file) {
		// URL to Targetfile
		String url = file;
		// Generate Graph of Targetfile
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToGraph(url, false);
		DepthFirstSearch.setGraph(G);

		// (only for tests in use) !!!
		readGraph(G, 0);
	}

	/**
	 * Enumerator of Node-Colors
	 */
	private static enum VertexState {
		WHITE, GRAY, BLACK
	}

	/**
	 * Read the Graph (First Iteration of Tree)
	 * 
	 * @param G
	 *            the @Graph
	 */
	public static void readGraph(@SuppressWarnings("rawtypes") Graph G,
			int startNode) {
		// Number of Nodes
		vertexCount = G.getNumberVertices();

		// Set all Vertices to WHITE
		VertexState state[] = new VertexState[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			state[i] = VertexState.WHITE;
			
			// Control-Output of Console
			System.out.println("Node: " + i + " -> " + state[i].toString());
			// g.setColor(Color.WHITE);
			// g.fillOval(pos[i][0], pos[i][1], 10, 10);
		}
		loopDFS(startNode, state);
	}

	/**
	 * Iteration in Tree
	 * 
	 * @param u
	 *            Parent-Node
	 * @param state
	 *            Color of Node (State)
	 */
	private static void loopDFS(int u, VertexState[] state) {
		state[u] = VertexState.GRAY;

		// Control-Output of Console
		System.out.println("Node: " + u + " -> " + state[u].toString());
		// g.setColor(Color.GRAY);
		// g.fillOval(pos[u][0], pos[u][1], 10, 10);

		for (int v = 0; v < vertexCount; v++) {
			if (isEdge(u, v) && state[v] == VertexState.WHITE) {
				loopDFS(v, state);
			}
		}
		state[u] = VertexState.BLACK;
		
		// Control-Output of Console
		System.out.println("Node: " + u + " -> " + state[u].toString());
		// g.setColor(Color.BLACK);
		// g.fillOval(pos[u][0], pos[u][1], 10, 10);
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
				// hier kann die GUI angeschlossen werden
				// hier wird bei true der Status von Weiß zu Grau zu Schwarz

				// gibt die daten in der console aus
				// System.out.println("looking from " + u + " -- to --> " + v);
				return true;
			}
		}
		return false;
	}

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
	 *            @Graph
	 */
	@SuppressWarnings("rawtypes")
	public static void setGraph(Graph g) {
		DepthFirstSearch.graph = g;
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
		DepthFirstSearch.pos = pos;
	}

	/**
	 * Graphics Getter
	 * 
	 * @return @Graphics
	 */
	public static Graphics getG() {
		return graphics;
	}

	/**
	 * Graphics Setter
	 * 
	 * @param g
	 *            @Graphics
	 */
	public static void setG(Graphics g) {
		DepthFirstSearch.graphics = g;
	}

}
