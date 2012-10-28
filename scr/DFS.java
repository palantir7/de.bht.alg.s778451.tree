import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.util.Collection;
import java.util.Iterator;

public class DFS {

	static int vertexCount;
	static Graph graph;

	/**
	 * @param args
	 */
	public static void run(String file) {
		// URL zu Zieldatei
		// String url = "F:\\GitHub\\de.bht.alg.s778451.tree\\scr\\dat\\graph20.txt";
		String url = file;
		// Lade Graph der Zieldatei
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToGraph(url, false);

		readGraph(G);

	}

	private static enum VertexState {
		White, Gray, Black
	}

	// liest den Graphen
	private static void readGraph(Graph G) {
		// Anzahl der Knoten
		vertexCount = G.getNumberVertices();
		graph = G;

		// Setzte alle Vertizes auf Weiﬂ
		VertexState state[] = new VertexState[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			state[i] = VertexState.White;
		}
		loopDFS(0, state);
	}

	// Iteriert ¸ber den Baum
	private static void loopDFS(int u, VertexState[] state) {
		System.out.println(u + " -> ");

		state[u] = VertexState.Gray;
		for (int v = 0; v < vertexCount; v++) {
			if (isEdge(u, v) && state[v] == VertexState.White) {
				loopDFS(v, state);
			}
		}
		state[u] = VertexState.Black;
	}

	// Schaut ob Punkt u und Punkt v eine Kante haben
	private static boolean isEdge(int u, int v) {
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				// hier kann die GUI angeschlossen werden
				// hier wird bei true der Status von Weiﬂ zu Grau zu Schwarz
				System.out.println(u + " -> " + neighbor + " -> " + v);
				return true;
			}
		}
		return false;
	}

}
