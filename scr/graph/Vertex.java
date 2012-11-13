package graph;

/**
 * Eine Klasse, die Knoten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public class Vertex {
	private int id;
	private double dist;
	private boolean seen;
	private Vertex prev;

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public Vertex(int id) {
		this.id = id;
	}
	
	public double getDist() {
		return dist;
	}
	
	public void setDist(double maxValue) {
		this.dist = maxValue;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return new Integer(id).toString();
	}
}