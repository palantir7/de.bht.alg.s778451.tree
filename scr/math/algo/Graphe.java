package math.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Graphe {

	private ArrayList<Arete> aretes = new ArrayList<Arete>();

	private Map<Integer, Set<Arete>> aretesAdjacentes = new HashMap<Integer, Set<Arete>>();

	private Set<Integer> peres = new HashSet<Integer>();

	private UnionFind unionFind;

	public Graphe() {
		unionFind = new UnionFind(0);
	}

	public Graphe(List<Arete> aretes) {
		for (Arete arete : aretes)
			ajouterArete(arete);
		unionFind = new UnionFind(nbSommets());
	}

	private void ajouterAretePrivate(Arete arete) {
		aretes.add(arete);
		lierSommetsAretes(arete);
		peres.add(arete.getU());
		peres.add(arete.getV());
	}

	public void ajouterArete(Arete arete) {
		ajouterAretePrivate(arete);
		unionFind = new UnionFind(nbSommets());
	}

	private void lierSommetsAretes(Arete arete) {
		lierSommetArete(arete.getU(), arete);
		lierSommetArete(arete.getV(), arete);
	}

	private void lierSommetArete(int sommet, Arete arete) {
		getAretesAdjacentes(sommet).add(arete);
	}

	public int nbSommets() {
		return aretesAdjacentes.size();
	}

	public Set<Integer> getSommets() {
		return peres;
	}

	public List<Arete> getAretes() {
		return aretes;
	}

	public int nbAretes() {
		return aretes.size();
	}

	public Set<Arete> getAretesAdjacentes(int sommet) {
		if (!aretesAdjacentes.containsKey(sommet)) aretesAdjacentes.put(sommet,
				new HashSet<Arete>());
		return aretesAdjacentes.get(sommet);
	}

	public void unir(Arete arete) {
		int u = arete.getU();
		getAretesAdjacentes(u).remove(arete);
		int v = arete.getV();
		getAretesAdjacentes(v).remove(arete);
		int pere_u = unionFind.find(u);
		int pere_v = unionFind.find(v);
		unionFind.union(u, v);
		int pere = unionFind.find(u);
		getAretesAdjacentes(pere).remove(arete);
		if (pere != pere_u) {
			getAretesAdjacentes(pere).addAll(getAretesAdjacentes(u));
			peres.remove(pere_u);
		}
		if (pere != pere_v) {
			getAretesAdjacentes(pere).addAll(getAretesAdjacentes(v));
			peres.remove(pere_v);
		}
	}

	public String toString() {
		String retour = "";
		for (Arete arete : aretes) {
			retour += arete.toString() + "\n";
		}
		return retour;
	}

}