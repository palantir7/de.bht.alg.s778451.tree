package algo;

public class UnionFind {
	private int[] parents;
	private int N;

	public UnionFind(int n) {
		parents = new int[n];
		for (int i = 0; i < n; ++i)
			parents[i] = n;
		this.N = n;
	}

	public int find(int c) {
		if (parents[c] >= N) return c;
		int f = find(parents[c]);
		parents[c] = f;
		return f;
	}

	public void link(int i, int j) {
		assert (find(i) != find(j));
		if (parents[i] < parents[j]) parents[i] = j;
		else if (parents[i] > parents[j]) parents[j] = i;
		else {
			parents[j] = i;
			++parents[i];
		}
	}

	public void union(int i, int j) {
		int fi = find(i), fj = find(j);
		if (fi != fj) link(fi, fj);
	}
}