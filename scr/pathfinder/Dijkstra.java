package pathfinder;

import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import paint.PaintPanel;
import pathfinder.tree.Edges;
import pathfinder.tree.Node;

public class Dijkstra {

	private static ArrayList<Node> nodeBuffer;
	private static PaintPanel paintArea;

	@SuppressWarnings("rawtypes")
	public static void run(String file, PaintPanel panel, String from, String to) throws Exception {
		// Initalisiere Buffer;
		nodeBuffer = new ArrayList<Node>();
		// Setzt PaintPanel
		paintArea = panel;
		// URL zum Targetfile
		String url = file;
		// Generiert einen Graph aus dem Targetfile
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToWeightedGraph(url,
				false);

		// Lese Knoten ein
		readNodes(G);
		// Wende Algo auf Kanten an
		useAlgo(G);

		// Uebergib die Knoten einem Array
		Node[] vertices = new Node[nodeBuffer.size()];
		vertices = nodeBuffer.toArray(vertices);

		// Berechne die Wege
		int startNode = Integer.parseInt(from);
		computePaths(nodeBuffer.get(startNode));

		// Zeichne alle Kanten ein
		for (Edge e : G.getEdges()) {
			((PaintPanel) paintArea).addEdge("GRAY", e.getVertexA().getId(), e
					.getVertexB().getId());
		}

		// Gib die kuerzeste Strecke zu einem Target Knoten aus
		System.out.println("Beginne mit Zeichnen ...");
		for (Node v : vertices) {
			// Target
			String target = to;
			String vertex = v.toString();

			if (vertex.equals(target)) {
				// Lösche PaintArea
				paintArea.clearArea();

				// Zeichne Knoden ein
				for (int i = 0; i < vertices.length; i++) {
					((PaintPanel) paintArea).addNode("WHITE", i);
					((PaintPanel) paintArea).addText("" + i, "BLACK", i);
				}

				// Zeiche Pfad ein
				List<Node> path = getShortestPathTo(v);

				// gib den Text in der PaintPanel aus
				String text = "Distance from " + from + " to " + to + ": "
						+ v.minDistance + "  Path: " + path;
				((PaintPanel) paintArea).addText(text, "BLACK", 99);

				// Zeichnet den genutzten Pfad rot ein
				for (int i = 0; i < path.size(); i++) {
					int von = Integer.parseInt(path.get(i).name);
					int zu = Integer.parseInt(path.get(i).toString());
					if (i > 0) {
						int n = i - 1;
						von = Integer.parseInt(path.get(n).toString());
					}
					((PaintPanel) paintArea).addEdge("RED", von, zu);
					((PaintPanel) paintArea).addNode("BLACK", zu);
				}
			}
		}
		System.out.println("Übergebe an PaintPanel ...");
	}

	/**
	 * @param G
	 */
	@SuppressWarnings("rawtypes")
	private static void readNodes(Graph G) {
		// Iteriere ueber die Knoten
		Iterator itr = G.getVertices().iterator();
		// verlager die Knoten in den Buffer
		while (itr.hasNext()) {
			Vertex v = (Vertex) itr.next();
			String name = "" + v.getId();
			Node n = new Node(name);
			nodeBuffer.add(n);
		}
		// Debugausgabe
		System.out.println("Knoten eingelesen ...");
	}

	/**
	 * @param G
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	private static void useAlgo(Graph G) throws Exception {
		// Sammle aus dem Graph die passenden Kanten zu den Knoten
		for (int n = 0; n < nodeBuffer.size(); n++) {
			ArrayList<Edges> edgeBuffer = new ArrayList<Edges>();
			Node node = nodeBuffer.get(n);
			int nodeI = Integer.parseInt(node.toString());

			// Schau ob die Kante mit dem Knoten verbunden ist
			Iterator itr = G.getEdges().iterator();
			while (itr.hasNext()) {
				Edge e = (Edge) itr.next();
				int focus = e.getVertexA().getId();

				if (focus == nodeI) {
					// Merke dir die Kante und dessen Laenge
					int traget = e.getVertexB().getId();
					// Wirf einen Fehler, wenn negative Kantenlängen da sind
					if (e.getWeight() < 0.) {
						throw new Exception();
					}
					Edges edge = new Edges(nodeBuffer.get(traget),
							e.getWeight());
					edgeBuffer.add(edge);
				}
			}

			// Gib die Kanten an den zustaendigen Knoten
			Edges[] nodeEdges = new Edges[edgeBuffer.size()];
			for (int i = 0; i < edgeBuffer.size(); i++) {
				nodeEdges[i] = edgeBuffer.get(i);
			}
			node.adjacencies = nodeEdges;
		}
		System.out.println("Kanten eingelesen ...");
	}

	/**
	 * @param source
	 */
	private static void computePaths(Node source) {
		// Setze die minimal Distanz auf 0
		source.minDistance = 0.;
		// Erstelle eine Queue als Gedaechtniss
		PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>();
		NodeQueue.add(source);

		// Falls die Queue nicht leer vergleiche Distanzen
		while (!NodeQueue.isEmpty()) {
			Node u = NodeQueue.poll();

			// Visit each edge exiting u
			for (Edges e : u.adjacencies) {
				Node v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				// Falls die Distanz kleiner als alte Distanz
				if (distanceThroughU < v.minDistance) {
					// Entferne den Knoten
					NodeQueue.remove(v);
					// Setze neue Distanz
					v.minDistance = distanceThroughU;
					v.previous = u;
					// Merke dir den Knoten
					NodeQueue.add(v);
				}
			}
		}
	}

	/**
	 * @param target
	 * @return
	 */
	private static List<Node> getShortestPathTo(Node target) {
		List<Node> path = new ArrayList<Node>();
		for (Node Node = target; Node != null; Node = Node.previous) {
			path.add(Node);
		}
		int startNode = Integer.parseInt(path.get(0).toString());
		int exitNode = Integer.parseInt(path.get(path.size() - 1).toString());

		// Zeichne Startknoten
		((PaintPanel) paintArea).addNode("RED", startNode);

		// Zeichne Ausgangsknoten
		((PaintPanel) paintArea).addNode("RED", exitNode);

		Collections.reverse(path);
		return path;
	}
}
