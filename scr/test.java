import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;


public class test {
	
	static int vertexCount;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// URL zu Zieldatei
		String url = "F:\\GitHub\\de.bht.alg.s778451.tree\\scr\\dat\\graph20.txt";
		// Lade Graph der Zieldatei
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToGraph(url, false);
		
		// Anzahl der Knoten
		vertexCount = G.getNumberVertices();

		
		// Nachbarn des Knotens
		G.getNeighbours(0);
		G.getVertices();
	}

}
