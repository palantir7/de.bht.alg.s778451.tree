package algo;

import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.util.Collection;
import java.util.Iterator;

import paint.PaintPanel;

public class DepthFirstSearch extends Thread {

	@SuppressWarnings("rawtypes")
	private static Graph graph;
	private static PaintPanel paintArea;
	private static int[][] pos;
	private static int vertexCount;

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
			((PaintPanel) paintArea).addNode("WHITE", i);
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
		((PaintPanel) paintArea).addNode("GRAY", u);

		for (int v = 0; v < vertexCount; v++) {
			if (isEdge(u, v) && state[v] == VertexState.WHITE) {
				loopDFS(v, state);
			}
		}
		state[u] = VertexState.BLACK;		
		((PaintPanel) paintArea).addNode("BLACK", u);
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
				((PaintPanel) paintArea).addEdge("GRAY", u, v);
				return true;
			}
		}
		return false;
	}
	
	//---------- Getten & Setter ---------->>>

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
}
