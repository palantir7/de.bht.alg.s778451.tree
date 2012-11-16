package graph;

/**
 * Eine Klasse, die Knoten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public class Vertex implements Comparable<Vertex> {
	private int id;
	private boolean seen;
	public Vertex previous;
	public double minDistance = Double.POSITIVE_INFINITY;

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public Vertex(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return new Integer(id).toString();
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
}