package pathfinder.tree;

public class Edges {
	public final Node target;
	public final double weight;

	public Edges(Node argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}
