package math.algo;

public class Arete implements Comparable<Arete> {
	private int u;

	private int v;

	private int poids;

	Arete(int u, int v, int poids) {
		this.u = u;
		this.v = v;
		this.poids = poids;
	}

	public int getU() {
		return u;
	}

	public int getV() {
		return v;
	}

	public int getPoids() {
		return poids;
	}

	public int compareTo(Arete o) {
		return o.poids - this.poids;
	}

	@Override
	public String toString() {
		return "Arete : u=" + u + " v=" + v + " poids=" + poids;
	}

}